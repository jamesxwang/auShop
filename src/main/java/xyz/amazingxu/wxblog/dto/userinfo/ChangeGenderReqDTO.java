package xyz.amazingxu.wxblog.dto.userinfo;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/9 13:26
 */
public class ChangeGenderReqDTO {
    @Override
    public String toString() {
        return super.toString();
    }

    private String oldGender;
    private String  newGender;

    public String getOldGender() {
        return oldGender;
    }

    public void setOldGender(String oldGender) {
        this.oldGender = oldGender;
    }

    public String getNewGender() {
        return newGender;
    }

    public void setNewGender(String newGender) {
        this.newGender = newGender;
    }
}
