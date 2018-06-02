package xyz.amazingxu.wxblog.service;

import xyz.amazingxu.wxblog.dto.ChangePasswordDTO;
import xyz.amazingxu.wxblog.dto.UserContextDTO;
import xyz.amazingxu.wxblog.dto.UserDTO;
import xyz.amazingxu.wxblog.dto.UserRegisterDTO;

/**
 *
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:43
 */
public interface IUserService {
    /**
     * Login(return JwtToken)
     * @param userDTO
     * @return
     */
    String getTokenByLogin(UserDTO userDTO);

    /**
     * Get user information by id
     * @param id
     * @return
     */
    UserContextDTO getUserContextById(String id);

    /**
     * Get current user information
     * @return
     */
    UserContextDTO getMyUserContext();

    /**
     * Register an account
     * @return
     */
    UserRegisterDTO register();

    /**
     * Change password for current user
     * @param changePasswordDTO
     */
    void changePassword(ChangePasswordDTO changePasswordDTO);

}
