package xyz.amazingxu.wxblog.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.amazingxu.core.utils.SystemVars;
import xyz.amazingxu.wxblog.dao.IUserDAO;
import xyz.amazingxu.wxblog.domain.UserDO;
import xyz.amazingxu.wxblog.dto.*;
import xyz.amazingxu.wxblog.dto.userinfo.*;
import xyz.amazingxu.wxblog.exception.wxblogException;
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
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

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
                    criteriaBuilder.equal(uidPath, userDTO.getUsername()),
                    criteriaBuilder.equal(pwdPath, userDTO.getPassword())
            );
        });
        if (userDO == null) {
            throw new wxblogException("Username or password incorrect!");
        } else if (userDO.getDeleted()) {
            throw new wxblogException("User has been banned!");
        } else {
            Map<String, Object> claims = new HashMap<>();
            UserContextDTO userContextDTO = getUserContextById(userDO.getId());
            claims.put("id", userContextDTO.getId());
            claims.put("name", userContextDTO.getName());
            claims.put("username", userContextDTO.getUsername());
            claims.put("email", userContextDTO.getEmail());
            claims.put("gender", userContextDTO.getGender());
            claims.put("phone", userContextDTO.getPhone());
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
        userContextDTO.setEmail(userDO.getEmail());
        userContextDTO.setGender(userDO.getGender());
        userContextDTO.setPhone(userDO.getPhone());
        return userContextDTO;
    }

    @Override
    public UserContextDTO getMyUserContext() {
        UserContextDTO user = getUserContext();
        return getUserContextById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        //TODO 细化报错信息
        //账号只能是字母开头，允许6-20字节，允许字母数字下划线
        String usernamePattern = "^[a-zA-Z][a-zA-Z0-9_]{5,19}$";
        //密码只能是0~9, a~z, A~Z,特殊字符包括  ^ % & ' , ; = ? $   长度为6~20
        String passwordPattern = "[\\u4E00-\\u9FA5A-Za-z0-9_^%&',;=?$]{6,20}";
        //标准邮箱格式
        String emailPattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

        if (userRegisterReqDTO.getUsername().equals("") || userRegisterReqDTO.getName().equals("") || userRegisterReqDTO.getPassword().equals("")) {
            throw new wxblogException("Username, Full name or Password is empty!");
        } else if (!userRegisterReqDTO.getUsername().matches(usernamePattern)){
            if (userRegisterReqDTO.getUsername().length()<6 || userRegisterReqDTO.getUsername().length()>20){
                throw new wxblogException("Username must be 6~20 characters!");
            }else {
                throw new wxblogException("Incorrect username format!");
            }
        } else if (userRegisterReqDTO.getName().length()>40){
                throw new wxblogException("Full Name must be 0~40 characters!");
        } else if (!userRegisterReqDTO.getPassword().matches(passwordPattern)) {
            if (userRegisterReqDTO.getPassword().length()<6 || userRegisterReqDTO.getPassword().length()>20){
                throw new wxblogException("Password must be 6~20 characters!");
            }else {
                throw new wxblogException("Password can only contain letters, numbers and ^%&',;=?$ !");
            }
        } else if (!userRegisterReqDTO.getEmail().matches(emailPattern)){
           throw new wxblogException("Incorrect e-mail format!");
        } else {
            //查找账号是否存在
            long count = userDAO.count((root, query, cb) -> {
                Path<String> usernamePath = root.get("username");
                return cb.equal(usernamePath, userRegisterReqDTO.getUsername());
            });
            if (count>0){
                throw new wxblogException("Account has already existed！");
            } else {
                UserDO userDO = userRegisterReqMapper.to(userRegisterReqDTO);
                userDAO.save(userDO);
            }
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

    @Override
    public void changePhone(ChangePhoneReqDTO changePhoneReqDTO) {
            UserContextDTO user = getMyUserContext();
            UserDO userDO = userDAO.findOne(user.getId());
            if (userDO.getPhone() != null){
                if (userDO.getPhone().equals(changePhoneReqDTO.getOldPhone())) {
                    userDO.setPhone(changePhoneReqDTO.getNewPhone());
                    userDAO.save(userDO);
                }else {
                    throw new wxblogException("Please check your original phone! ");
                }
            } else  {
                userDO.setPhone(changePhoneReqDTO.getNewPhone());
                userDAO.save(userDO);
            }
    }

    @Override
    public void changeName(ChangeNameReqDTO changeNameReqDTO) {
        UserContextDTO user = getUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        userDO.setName(changeNameReqDTO.getNewName());
        userDAO.save(userDO);
    }

    @Override
    public void changeGender(ChangeGenderReqDTO changeGenderReqDTO) {
        //TODO check
        UserContextDTO user = getUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        if (userDO.getGender() != null) {
            if (userDO.getGender().equals(changeGenderReqDTO.getOldGender())){
                userDO.setGender(changeGenderReqDTO.getNewGender());
                userDAO.save(userDO);
            }
        } else {
            userDO.setGender(changeGenderReqDTO.getNewGender());
            userDAO.save(userDO);
        }
    }

    @Override
    public void changeEmail(ChangeEmailReqDTO changeEmailReqDTO) {
        //TODO change email service implements
        UserContextDTO user = getUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        userDO.setEmail(changeEmailReqDTO.getNewEmail());
        userDAO.save(userDO);
    }
}
