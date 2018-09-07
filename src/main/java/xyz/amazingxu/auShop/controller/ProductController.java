package xyz.amazingxu.auShop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.amazingxu.core.utils.WebResults;
import xyz.amazingxu.core.utils.WebUtils;
import xyz.amazingxu.auShop.dto.product.ProductDTO;
import xyz.amazingxu.auShop.dto.product.ProductQueryDTO;
import xyz.amazingxu.auShop.service.IProductService;

import java.util.List;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/27 17:50
 */
@Api(value = "product",description = "商品")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "商品查询，返回符合条件的结果列表")
    @PostMapping("query")
    WebResults productQuery(@RequestBody ProductQueryDTO productQueryDTO){
        return WebUtils.success(productService.productQuery(productQueryDTO));
    }

    @ApiOperation(value = "根据商品表的主键获取一个商品详细信息")
    @PostMapping("getProductById")
    WebResults getProductById(@RequestBody String prod_id){
        return WebUtils.success(productService.getProductById(prod_id));
    }

}
