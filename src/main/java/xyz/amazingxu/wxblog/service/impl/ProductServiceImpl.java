package xyz.amazingxu.wxblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.amazingxu.wxblog.dao.IProductDAO;
import xyz.amazingxu.wxblog.domain.ProductDO;
import xyz.amazingxu.wxblog.dto.product.ProductDTO;
import xyz.amazingxu.wxblog.dto.product.ProductQueryDTO;
import xyz.amazingxu.wxblog.mapper.ProductMapper;
import xyz.amazingxu.wxblog.service.IProductService;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.*;


/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/27 18:04
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDAO productDAO;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductDTO getProductById(String prod_id) {
        ProductDO productDO = productDAO.findOne(prod_id);
        return productMapper.from(productDO);    }

    public List<ProductDTO> productQuery(ProductQueryDTO productQueryDTO) {
        List<ProductDTO> result = null;
        Specification specification = (Specification<ProductDTO>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productQueryDTO == null){
                //TODO 返回所有商品
                return null;
            } else {
                //TODO 搜索条件优化
                if (null != productQueryDTO.getProd_name()){
                    predicates.add(cb.like(root.get("prod_name"),"%"+productQueryDTO.getProd_name()+"%"));
                }
                else if (null != productQueryDTO.getProd_category()){
                    predicates.add(cb.like(root.get("prod_category"),"%"+productQueryDTO.getProd_category()+"%"));
                }
                else if (null != productQueryDTO.getProd_price()){
                    predicates.add(cb.lessThan(root.get("prod_price"),productQueryDTO.getProd_price()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        result = productDAO.findAll(specification);
        return result;
    }
}
