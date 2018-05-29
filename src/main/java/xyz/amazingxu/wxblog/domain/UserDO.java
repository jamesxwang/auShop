package xyz.amazingxu.wxblog.domain;

import xyz.amazingxu.core.BaseDO;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 15:33
 */
@Entity(name = "userinfo")
public class UserDO extends BaseDO{

    @Override
    public String toString() {
        return "UserDO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Column(name = "name",length = 40)
    private String name;
    @Column(name = "username",unique=true,nullable = false , length = 20)
    private String username;
    @Column(name = "password",nullable = false , length = 20)
    private String password;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
