package xyz.amazingxu.core;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.amazingxu.auShop.dto.UserContextDTO;

import java.util.Date;
import java.util.UUID;

/**
 * Data Transfer Object base class
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 17:25
 */

public abstract class BaseDTO {

    public BaseDTO(){
        this.id = UUID.randomUUID().toString();
        this.deleted = false;
//        try {
//            UserContextDTO userContextDTO = (UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            this.createUser = userContextDTO.getUsername();
//        } catch (Exception e) {
//            this.createUser = "dev";
//        }
        this.createTime = new Date();
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id='" + id + '\'' +
                ", deleted=" + deleted +
//                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(hidden = true)
    private Boolean deleted;
//    @ApiModelProperty(hidden = true)
//    private String createUser;
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

//    public String getCreateUser() {
//        return createUser;
//    }
//
//    public void setCreateUser(String createUser) {
//        this.createUser = createUser;
//    }

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
