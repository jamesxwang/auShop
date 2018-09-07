package xyz.amazingxu.auShop.service.impl;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import xyz.amazingxu.auShop.dto.UserContextDTO;
import xyz.amazingxu.auShop.dto.product.ProductDTO;
import xyz.amazingxu.auShop.service.IBaseService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.Map;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/23 10:25
 */
public class BaseServiceImpl implements IBaseService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public UserContextDTO getUserContext() {
        UserContextDTO userContextDTO = (UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userContextDTO;
    }

    @Override
    public List<Map> sqlQuery(String sql) {
        return sqlQuery(sql,null);
    }

    @Override
    public List<Map> sqlQuery(String sql, Object[] params) {
        Query query  = entityManager.createNativeQuery(sql);
        if (params!=null){
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i+1,params);
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }
}
