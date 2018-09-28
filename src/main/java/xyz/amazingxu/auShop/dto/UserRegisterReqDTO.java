package xyz.amazingxu.auShop.dto;

import xyz.amazingxu.core.BaseDTO;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/4 11:23
 */
public class UserRegisterReqDTO extends BaseDTO {
    @Override
    public String toString() {
        return "UserDTO{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String username;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}