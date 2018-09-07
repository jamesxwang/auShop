package xyz.amazingxu.auShop.service;

import xyz.amazingxu.auShop.dto.*;
import xyz.amazingxu.auShop.dto.userinfo.*;

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
     * refreshToken
     * @return
     */
    String refreshToken();

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
    void register(UserRegisterReqDTO userRegisterReqDTO);

    /**
     * Change password for current user
     * @param changePasswordReqDTO
     */
    void changePassword(ChangePasswordReqDTO changePasswordReqDTO);

    /**
     * Update phone number for current user
     * @param changePhoneReqDTO
     */
    void changePhone(ChangePhoneReqDTO changePhoneReqDTO);

    /**
     * Update username for current user
     * @param changeNameReqDTO
     */
    void changeName(ChangeNameReqDTO changeNameReqDTO);

    /**
     * Update gender for current user
     * @param changeGenderReqDTO
     */
    void changeGender(ChangeGenderReqDTO changeGenderReqDTO);

    /**
     * Update Email for current user
     * @param changeEmailReqDTO
     */
    void changeEmail(ChangeEmailReqDTO changeEmailReqDTO);

}
