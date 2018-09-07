package xyz.amazingxu.auShop.dto;

import xyz.amazingxu.core.BaseDTO;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/1 17:23
 */
public class UserDTO extends BaseDTO{
    @Override
    public String toString() {
        return "UserDTO{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String username;
    private  String password;

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
