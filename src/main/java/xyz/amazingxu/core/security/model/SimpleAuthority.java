package xyz.amazingxu.core.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/4/27 9:58
 */
public class SimpleAuthority implements GrantedAuthority {

    @Override
    public String toString() {
        return "SimpleAuthority{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                '}';
    }

    private String code;
    private String name;
    private String url;
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return type+";"+url;
    }
}
