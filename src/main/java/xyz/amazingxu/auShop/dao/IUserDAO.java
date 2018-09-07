package xyz.amazingxu.auShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.amazingxu.auShop.domain.UserDO;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:10
 */
public interface IUserDAO extends JpaRepository<UserDO,String>,JpaSpecificationExecutor<UserDO>{
}
