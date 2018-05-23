package xyz.amazingxu.wxblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.amazingxu.core.utils.WebResults;
import xyz.amazingxu.core.utils.WebUtils;
import xyz.amazingxu.wxblog.dto.ChangePasswordDTO;
import xyz.amazingxu.wxblog.dto.UserDTO;
import xyz.amazingxu.wxblog.service.IUserService;

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

    @ApiOperation(value = "修改密码")
    @PostMapping("changePassword")
    public WebResults changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        userService.changePassword(changePasswordDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping
    public WebResults getMyUserContext(){
        return WebUtils.success(userService.getMyUserContext());
    }

}
