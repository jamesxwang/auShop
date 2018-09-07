package xyz.amazingxu.auShop.dto.userinfo;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 22:00
 */
public class ChangePasswordReqDTO {
    @Override
    public String toString() {
        return super.toString();
    }

    private String oldPassword;
    private String newPassword;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
