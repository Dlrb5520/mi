package com.mi.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mi.entity.system.Menu;

import java.util.List;

/**
 * @InterfaceName MenuMapper
 * @Author yangli
 * @Date 2021/7/26 15:03
 * @Description:
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询用户权限
     * @param username 用户名称
     * @return
     */
    List<Menu> findUserPermissions(String username);
}
