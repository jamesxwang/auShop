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

    @Column(name = "user_id",length = 40)
    private String user_id;

    @Column
    private String username;

}
