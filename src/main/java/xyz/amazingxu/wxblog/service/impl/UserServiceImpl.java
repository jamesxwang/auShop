package xyz.amazingxu.wxblog.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.amazingxu.core.utils.SystemVars;
import xyz.amazingxu.wxblog.dao.IUserDAO;
import xyz.amazingxu.wxblog.domain.UserDO;
import xyz.amazingxu.wxblog.dto.ChangePasswordReqDTO;
import xyz.amazingxu.wxblog.dto.UserContextDTO;
import xyz.amazingxu.wxblog.dto.UserDTO;
import xyz.amazingxu.wxblog.dto.UserRegisterReqDTO;
import xyz.amazingxu.wxblog.exception.wxblogException;
import xyz.amazingxu.wxblog.mapper.UserMapper;
import xyz.amazingxu.wxblog.mapper.UserRegisterReqMapper;
import xyz.amazingxu.wxblog.service.IUserService;

import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User Service Implements
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:44
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private UserRegisterReqMapper userRegisterReqMapper;

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
            throw new RuntimeException("Username or password incorrect!");
        }else if (userDO.getDeleted()){
            throw new RuntimeException("The user has been banned!");
        }else {
            Map<String, Object> claims = new HashMap<>();
            UserContextDTO userContextDTO = getUserContextById(userDO.getId());
            claims.put("id",userContextDTO.getId());
            claims.put("name",userContextDTO.getName());
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
    public UserContextDTO getUserContextById(String id) {
        UserDO userDO = userDAO.findOne(id);
        UserContextDTO userContextDTO = new UserContextDTO();
            userContextDTO.setId(userDO.getId());
            userContextDTO.setName(userDO.getName());
            userContextDTO.setUsername(userDO.getUsername());
        return  userContextDTO;
    }

    @Override
    public UserContextDTO getMyUserContext() {
        UserContextDTO user = getUserContext();
        return getUserContextById(user.getId());
    }

    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        UserDO userDO = userDAO.findOne(((root, query, cb) -> {
            Path<String> namePath = root.get("username");
            return cb.equal(namePath,userRegisterReqDTO.getUsername());
        }));

        if (userDO == null){
            userDO = userRegisterReqMapper.to(userRegisterReqDTO);
            userDAO.save(userDO);
        }else {
            throw new wxblogException("Account has already existed！");
        }
    }


    @Override
    public void changePassword(ChangePasswordReqDTO changePasswordReqDTO) {
        UserContextDTO user = getMyUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        if (userDO != null && userDO.getPassword().equals(changePasswordReqDTO.getOldPassword())){
            userDO.setPassword(changePasswordReqDTO.getNewPassword());
            userDAO.save(userDO);
        }else {
            throw new wxblogException("Please check your original password！");
        }
    }
}
