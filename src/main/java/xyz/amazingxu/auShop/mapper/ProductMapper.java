package xyz.amazingxu.auShop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import xyz.amazingxu.auShop.domain.ProductDO;
import xyz.amazingxu.auShop.dto.product.ProductDTO;
import xyz.amazingxu.auShop.dto.product.ProductQueryDTO;

import java.util.List;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/27 17:56
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    /**
     * ProductDO to ProductDTO
     * @param productDO
     * @return
     */
    @Mappings({
            @Mapping(source = "prod_id",target = "prod_id"),
            @Mapping(source = "deleted",target = "deleted"),
            @Mapping(source = "prod_name", target = "prod_name"),
            @Mapping(source = "prod_price", target = "prod_price"),
            @Mapping(source = "prod_category", target = "prod_category"),
            @Mapping(source = "prod_amount", target = "prod_amount"),
            @Mapping(source = "prod_size", target = "prod_size")
    })
    public ProductDTO from (ProductDO productDO);

    /**
     * ProductDTO to ProductDO
     * @param productDTO
     * @return
     */
    public ProductDO to (ProductDTO productDTO);

    /**
     * List<ProductDO> to List<ProductDTO>
     * @param productDOS
     * @return
     */
    public List<ProductDTO> from (List<ProductDO> productDOS);

    /**
     * List<ProductDTO> to List<ProductDO>
     * @param productDTOS
     * @return
     */
    public List<ProductDO> to (List<ProductDTO> productDTOS);

}
