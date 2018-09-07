package xyz.amazingxu.auShop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.amazingxu.core.utils.WebResults;
import xyz.amazingxu.core.utils.WebUtils;
import xyz.amazingxu.auShop.dto.*;
import xyz.amazingxu.auShop.dto.userinfo.*;
import xyz.amazingxu.auShop.service.IUserService;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:35
 */
@Api(value = "user",description = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public WebResults login(@RequestBody UserDTO userDTO){
        String token = userService.getTokenByLogin(userDTO);
        return WebUtils.success(token);
    }

    @ApiOperation(value = "刷新登录信息")
    @PostMapping("refresh")
    public WebResults refreshToken(){
        String newToken = userService.refreshToken();
        return WebUtils.success(newToken);
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("getMyUserContext")
    public WebResults getMyUserContext(){
        return WebUtils.success(userService.getMyUserContext());
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public WebResults register(@RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        userService.register(userRegisterReqDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("changePassword")
    public WebResults changePassword(@RequestBody ChangePasswordReqDTO changePasswordReqDTO){
        userService.changePassword(changePasswordReqDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "修改手机")
    @PostMapping("changePhone")
    public WebResults changePhone(@RequestBody ChangePhoneReqDTO changePhoneReqDTO){
        userService.changePhone(changePhoneReqDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "更改昵称")
    @PostMapping("changeName")
    public WebResults changeName(@RequestBody ChangeNameReqDTO changeNameReqDTO){
        userService.changeName(changeNameReqDTO);
        return WebUtils.success();
    }


    @ApiOperation(value = "更改性别")
    @PostMapping("changeGender")
    public WebResults changeGender(@RequestBody ChangeGenderReqDTO changeGenderReqDTO){
        userService.changeGender(changeGenderReqDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "更改邮箱")
    @PostMapping("changeEmail")
    public WebResults changeEmail(@RequestBody ChangeEmailReqDTO changeEmailReqDTO){
        userService.changeEmail(changeEmailReqDTO);
        return WebUtils.success();
    }
}
