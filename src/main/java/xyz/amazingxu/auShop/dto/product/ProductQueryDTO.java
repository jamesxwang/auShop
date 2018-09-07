package xyz.amazingxu.auShop.dto.product;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/30 11:51
 */
public class ProductQueryDTO {
    @Override
    public String toString() {

        return "ProductQueryDTO{" +
//                "prod_id='" + prod_id + '\'' +
                "prod_name='" + prod_name + '\'' +
                "prod_price='" + prod_price + '\'' +
                "prod_category='" + prod_category + '\'' +
                '}';
    }



//    private String prod_id;
    private String prod_name;
    private Float prod_price;
    private String prod_category;

//    public String getProd_id() {
//        return prod_id;
//    }

//    public void setProd_id(String prod_id) {
//        this.prod_id = prod_id;
//    }

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

}
