package xyz.amazingxu.wxblog.dto;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/1 17:23
 */
public class UserRegisterDTO {
    @Override
    public String toString() {
        return "UserContextDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
//                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
                '}';
    }
    private String name;
    private String username;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String id;
//    private Boolean gender;
    private String email;
//    private String phone;
}
