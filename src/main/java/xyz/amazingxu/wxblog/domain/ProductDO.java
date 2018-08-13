package xyz.amazingxu.wxblog.domain;

import xyz.amazingxu.core.BaseDO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/25 15:16
 */

/**
 * < product_detail > table
 */
@Entity(name = "product")
public class ProductDO {

    public ProductDO(){
        this.deleted = false;
    }

    @Override
    public String toString() {

        return "ProductDO{" +
                "prod_id='" + prod_id + '\'' +
                "deleted='" + deleted + '\'' +
                "prod_name='" + prod_name + '\'' +
                "prod_price='" + prod_price + '\'' +
                "prod_category='" + prod_category + '\'' +
                "prod_number='" + prod_amount + '\'' +
                "prod_size='" + prod_size + '\'' +
                '}';
    }

    @Id
    @Column(name = "prod_id", nullable = false,length = 40,unique = true)
    private String prod_id;
    @Column(name = "prod_name", nullable = false)
    private String prod_name;
    @Column(name = "prod_price", nullable = false, length = 10)
    private Float prod_price;
    @Column(name = "prod_category", nullable =false, length = 20)
    private String prod_category;
    @Column(name = "prod_amount",length = 10)
    private int prod_amount;
    @Column(name = "prod_size",nullable = false, length = 10)
    private String prod_size;
    @Column(name = "is_deleted",nullable = false)
    private boolean deleted;


    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public Float getProd_price() {
        return prod_price;
    }

    public void setProd_price(Float prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_category() {
        return prod_category;
    }

    public void setProd_category(String prod_category) {
        this.prod_category = prod_category;
    }

    public int getProd_amount() {
        return prod_amount;
    }

    public void setProd_amount(int prod_amount) {
        this.prod_amount = prod_amount;
    }

    public String getProd_size() {
        return prod_size;
    }

    public void setProd_size(String prod_size) {
        this.prod_size = prod_size;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
