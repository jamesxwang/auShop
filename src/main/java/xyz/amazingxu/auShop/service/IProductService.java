package xyz.amazingxu.auShop.service;

import xyz.amazingxu.auShop.dto.product.ProductDTO;
import xyz.amazingxu.auShop.dto.product.ProductQueryDTO;

import java.util.List;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/27 18:05
 */
public interface IProductService {

    /**
     * Find product by prod_id
     * @param prod_id
     * @return
     */
    ProductDTO getProductById(String prod_id);

    // 根据ProductQueryDTO里的值进行商品查询，返回符合条件的结果列表

    /**
     * Query products with requirements
     * @param productQueryDTO
     * @return
     */
    List<ProductDTO> productQuery(ProductQueryDTO productQueryDTO);
}
