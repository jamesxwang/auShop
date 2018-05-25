package xyz.amazingxu.wxblog.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.amazingxu.core.utils.SystemVars;
import xyz.amazingxu.wxblog.dao.IUserDAO;
import xyz.amazingxu.wxblog.domain.UserDO;
import xyz.amazingxu.wxblog.dto.ChangePasswordDTO;
import xyz.amazingxu.wxblog.dto.UserContextDTO;
import xyz.amazingxu.wxblog.dto.UserDTO;
import xyz.amazingxu.wxblog.exception.wxblogException;
import xyz.amazingxu.wxblog.service.IUserService;

import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:44
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{

    @Autowired
    IUserDAO userDAO;

    @Override
    public String getTokenByLogin(UserDTO userDTO) {
        String token = null;
        UserDO userDO = userDAO.findOne((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> uidPath = root.get("username");
            Path<String> pwdPath = root.get("password");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(uidPath,userDTO.getUsername()),
                    criteriaBuilder.equal(pwdPath,userDTO.getPassword())
            );
        });
        if (userDO == null){
            throw new RuntimeException("用户名或密码不正确！");
        }else if (userDO.getDeleted()){
            throw new RuntimeException("该用户已被禁用！");
        }else {
            Map<String, Object> claims = new HashMap<>();
            UserContextDTO userContextDTO = getUserContextByUserId(userDO.getId());
            claims.put("user_id",userContextDTO.getUserId());
            claims.put("username",userContextDTO.getUsername());
            token = Jwts.builder()
                    .setSubject(userDO.getId())
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                    .signWith(SignatureAlgorithm.HS512, SystemVars.JWT_SECRET)
                    .compact();
        }
        return token;
    }

    @Override
    public UserContextDTO getUserContextByUserId(String userId) {
        StringBuffer stringBufferSql = new StringBuffer();
        stringBufferSql.append("SELECT a.id AS user_id, a.username AS username, a.remark AS remark ");
        stringBufferSql.append("FROM userinfo a ");
        stringBufferSql.append("WHERE a.id = ?");
        List<Map> userResult = sqlQuery(stringBufferSql.toString(),new Object[]{userId});
        UserContextDTO userContextDTO = new UserContextDTO();
        if (userResult.size() != 0){
            Map user = userResult.get(0);
            userContextDTO.setUserId((String)user.get("userId"));
            userContextDTO.setUsername((String)user.get("username"));
        }
        return  userContextDTO;
    }

    @Override
    public UserContextDTO getMyUserContext() {
        UserContextDTO user = getUserContext();
        return getUserContextByUserId(user.getUserId());
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        UserContextDTO user = getMyUserContext();
        UserDO userDO = userDAO.findOne(user.getUserId());
        if (userDO != null && userDO.getPassword().equals(changePasswordDTO.getOldPassword())){
            userDO.setPassword(changePasswordDTO.getNewPassword());
            userDAO.save(userDO);
        }else {
            throw new wxblogException("原始密码不正确！");
        }
    }
}
