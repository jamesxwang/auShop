package xyz.amazingxu.auShop.dto.userinfo;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/9 12:06
 */
public class ChangePhoneReqDTO {
    @Override
    public String toString() {
        return super.toString();
    }

    private String oldPhone;
    private String newPhone;


    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

}
