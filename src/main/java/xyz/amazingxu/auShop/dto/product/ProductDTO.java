package xyz.amazingxu.auShop.dto.product;


import io.swagger.annotations.ApiModelProperty;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/27 17:47
 */
public class ProductDTO {

    @Override
    public String toString() {

        return "ProductDTO{" +
                "prod_id='" + prod_id + '\'' +
                "deleted='" + deleted + '\'' +
                "prod_name='" + prod_name + '\'' +
                "prod_price='" + prod_price + '\'' +
                "prod_category='" + prod_category + '\'' +
                "prod_amount='" + prod_amount + '\'' +
                "prod_size='" + prod_size + '\'' +
                '}';
    }




    private String prod_id;
    private String prod_name;
    private Float prod_price;
    private String prod_category;
    private int prod_amount;
    private String prod_size;
    @ApiModelProperty(hidden = true)
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
