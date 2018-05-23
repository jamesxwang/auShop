package xyz.amazingxu.wxblog.dto;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:50
 */
public class UserContextDTO {
    @Override
    public String toString() {
        return "UserContextDTO{" +
                "userId=" + userId + '\'' +
                ", username='" + username + '\'' +
                ", mainUrl='" + mainUrl + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    private String userId;
    private String username;
    private String mainUrl;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
