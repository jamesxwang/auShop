package xyz.amazingxu.wxblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.amazingxu.wxblog.domain.ProductDO;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/27 17:48
 */
public interface IProductDAO extends JpaRepository<ProductDO,String>,JpaSpecificationExecutor<ProductDO> {
}
