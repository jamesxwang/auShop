package xyz.amazingxu.wxblog.service;

import xyz.amazingxu.wxblog.dto.UserContextDTO;

import java.util.List;
import java.util.Map;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/23 10:25
 */
public interface IBaseService {
    /**
     * 获取登陆用户上下文
     * @return
     */
    UserContextDTO getUserContext();

    /**
     * 原生Sql查询
     * @param sql
     * @return
     */
    List<Map> sqlQuery(String sql);

    /**
     * 原生Sql查询，带参数
     * @param sql
     * @param params
     * @return
     */
    List<Map> sqlQuery(String sql,Object[] params);
}
