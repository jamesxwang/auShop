package xyz.amazingxu.auShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.amazingxu.auShop.domain.UserDO;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/4 9:44
 */
public interface IUserRegisterDAO extends JpaRepository<UserDO,String>,JpaSpecificationExecutor<UserDO> {
}
