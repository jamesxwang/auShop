package xyz.amazingxu.core;

import javax.persistence.*;

/**
 * 领域基类
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/18 22:38
 */

@MappedSuperclass
public abstract class BaseDO {

    /**
     * 主键id
     */
    @Id
    @Column(nullable = false,length = 40)
   private String id;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted",nullable = false)
    private boolean deleted;


}
