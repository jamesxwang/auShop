package xyz.amazingxu.core;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.UUID;

/**
 * DTO 的基类
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 17:25
 */
public abstract class BaseDTO {

    public BaseDTO(){
        this.id = UUID.randomUUID().toString();
        this.deleted = false;
        this.createUser = "dev";
        this.createTime = new Date();
    }

    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(hidden = true)
    private Boolean deleted;
    @ApiModelProperty(hidden = true)
    private String createUser;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
