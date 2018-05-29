package xyz.amazingxu.wxblog.dto;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:50
 */
public class UserContextDTO {
    @Override
    public String toString() {
        return "UserContextDTO{" +
                "Id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private String name;
    private String username;
    private String id;

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





}
