package xyz.amazingxu.wxblog.service;

import xyz.amazingxu.wxblog.dto.ChangePasswordDTO;
import xyz.amazingxu.wxblog.dto.UserContextDTO;
import xyz.amazingxu.wxblog.dto.UserDTO;

/**
 *
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:43
 */
public interface IUserService {
    /**
     * 登陆(返回JwtToken)
     * @param userDTO
     * @return
     */
    String getTokenByLogin(UserDTO userDTO);

    /**
     * 根据userId获取用户信息
     * @param userId
     * @return
     */
    UserContextDTO getUserContextByUserId(String userId);

    /**
     * 获取当前用户信息
     * @return
     */
    UserContextDTO getMyUserContext();

    /**
     * 当前用户更改密码
     * @param changePasswordDTO
     */
    void changePassword(ChangePasswordDTO changePasswordDTO);

}
