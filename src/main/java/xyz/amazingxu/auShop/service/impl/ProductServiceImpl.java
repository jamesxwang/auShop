package xyz.amazingxu.auShop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.amazingxu.auShop.dao.IProductDAO;
import xyz.amazingxu.auShop.domain.ProductDO;
import xyz.amazingxu.auShop.dto.product.ProductDTO;
import xyz.amazingxu.auShop.dto.product.ProductQueryDTO;
import xyz.amazingxu.auShop.mapper.ProductMapper;
import xyz.amazingxu.auShop.service.IProductService;

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
        return productMapper.from(productDO);
    }

    @SuppressWarnings("unchecked")
    public List<ProductDTO> productQuery(ProductQueryDTO productQueryDTO) {
        List<ProductDTO> result = null;
        if (productQueryDTO == null){
            return productMapper.from(productDAO.findAll());
        }else {
            Specification specification = (Specification<ProductDTO>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                //TODO - 搜索条件优化
                if (null != productQueryDTO.getProd_name()) {
                    predicates.add(cb.like(root.get("prod_name"), "%" + productQueryDTO.getProd_name() + "%"));
                }
                if (null != productQueryDTO.getProd_category()) {
                    predicates.add(cb.like(root.get("prod_category"), "%" + productQueryDTO.getProd_category() + "%"));
                }
                if (null != productQueryDTO.getProd_price()) {
                    predicates.add(cb.lessThan(root.get("prod_price"), productQueryDTO.getProd_price()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            };

        result = productMapper.from(productDAO.findAll(specification));
        return result;
        }
    }

}
