package xyz.amazingxu.auShop.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.amazingxu.core.utils.SystemVars;
import xyz.amazingxu.core.utils.WebUtils;
import xyz.amazingxu.auShop.dao.IUserDAO;
import xyz.amazingxu.auShop.domain.UserDO;
import xyz.amazingxu.auShop.dto.*;
import xyz.amazingxu.auShop.dto.userinfo.*;
import xyz.amazingxu.auShop.exception.auShopException;
import xyz.amazingxu.auShop.mapper.UserRegisterReqMapper;
import xyz.amazingxu.auShop.service.IUserService;

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
    //账号只能是字母开头，允许6-20字节，允许字母数字下划线
    String usernamePattern = "^[a-zA-Z][a-zA-Z0-9_]{5,19}$";
    //密码只能是0~9, a~z, A~Z和下划线，长度为6~20
    String passwordPattern = "[A-Za-z0-9_]{6,20}";
    //标准邮箱格式
    String emailPattern = "(\\w|-)+(\\.(\\w|-)+)*@(\\w|-)+(\\.(\\w|-)+)*(\\.[a-z]{2,4})";


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
            throw new auShopException("Incorrect username or password!");
        } else if (userDO.getDeleted()) {
            throw new auShopException("User has been banned!");
        } else {
            Map<String, Object> claims = new HashMap<>();
            UserContextDTO userContextDTO  = getUserContextById(userDO.getId());
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
    public String refreshToken() {
        String newToken = null;
        WebUtils webUtils = new WebUtils();
        UserContextDTO userContextDTO = (UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDO userDO = userDAO.findOne(userContextDTO.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDO.getId());
        claims.put("name", userDO.getName());
        claims.put("username", userDO.getUsername());
        claims.put("email", userDO.getEmail());
        claims.put("gender", userDO.getGender());
        claims.put("phone", userDO.getPhone());
        newToken = Jwts.builder()
                .setSubject(userDO.getId())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, SystemVars.JWT_SECRET)
                .compact();
        //存到session里
        UsernamePasswordAuthenticationToken authenticationToken = webUtils.getAuthentication (newToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return newToken;
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
        if (userRegisterReqDTO.getUsername().equals("") ||userRegisterReqDTO.getEmail().equals("")|| userRegisterReqDTO.getPassword().equals("")) {
            throw new auShopException("Register information cannot be null!");
        } else if (!userRegisterReqDTO.getUsername().matches(usernamePattern)){
            if (userRegisterReqDTO.getUsername().length()<6 || userRegisterReqDTO.getUsername().length()>20){
                throw new auShopException("Username must be 6 to 20 characters!");
            }else {
                throw new auShopException("Username must begins with a letter!");
            }
        } else if (!userRegisterReqDTO.getPassword().matches(passwordPattern)) {
            if (userRegisterReqDTO.getPassword().length()<6 || userRegisterReqDTO.getPassword().length()>20){
                throw new auShopException("Password must be 6 to 20 characters!");
            }else {
                throw new auShopException("Password can only contain letters, numbers and underscores!");
            }
        } else if (!userRegisterReqDTO.getEmail().matches(emailPattern)){
           throw new auShopException("Incorrect email!");
        } else {
            //查找账号是否存在
            long count = userDAO.count((root, query, cb) -> {
                Path<String> usernamePath = root.get("username");
                return cb.equal(usernamePath, userRegisterReqDTO.getUsername());
            });
            if (count>0){
                throw new auShopException("The username already exists!");
            } else {
                UserDO userDO = userRegisterReqMapper.to(userRegisterReqDTO);
                userDAO.save(userDO);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(ChangePasswordReqDTO changePasswordReqDTO) {
        UserContextDTO user = getMyUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        if (userDO != null && userDO.getPassword().equals(changePasswordReqDTO.getOldPassword())){
            if (changePasswordReqDTO.getNewPassword().matches(passwordPattern)){
                userDO.setPassword(changePasswordReqDTO.getNewPassword());
                userDAO.save(userDO);
            }else {
                throw new auShopException("密码格式不正确！");
            }
        }else {
            throw new auShopException("请检查原密码！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePhone(ChangePhoneReqDTO changePhoneReqDTO) {
            UserContextDTO user = getMyUserContext();
            UserDO userDO = userDAO.findOne(user.getId());
            if (userDO.getPhone() != null){
                if (userDO.getPhone().equals(changePhoneReqDTO.getOldPhone())) {
                    userDO.setPhone(changePhoneReqDTO.getNewPhone());
                    userDAO.save(userDO);
                }else {
                    throw new auShopException("请检查原手机号！");
                }
            } else  {
                userDO.setPhone(changePhoneReqDTO.getNewPhone());
                userDAO.save(userDO);
            }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeName(ChangeNameReqDTO changeNameReqDTO) {
        UserContextDTO user = getUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        userDO.setName(changeNameReqDTO.getNewName());
        userDAO.save(userDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeGender(ChangeGenderReqDTO changeGenderReqDTO) {
        UserContextDTO user = getUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        if (!changeGenderReqDTO.getNewGender().equals("")){
            userDO.setGender(changeGenderReqDTO.getNewGender());
            userDAO.save(userDO);
        } else {
            userDO.setGender("Unknown");
            userDAO.save(userDO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeEmail(ChangeEmailReqDTO changeEmailReqDTO) {
        UserContextDTO user = getUserContext();
        UserDO userDO = userDAO.findOne(user.getId());
        if (changeEmailReqDTO.getNewEmail().matches(emailPattern)) {
            userDO.setEmail(changeEmailReqDTO.getNewEmail());
            userDAO.save(userDO);
        } else {
            throw new auShopException("邮箱格式不正确！");
        }
    }
}
