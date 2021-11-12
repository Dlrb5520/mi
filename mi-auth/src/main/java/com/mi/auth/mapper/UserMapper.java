package com.mi.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mi.entity.system.SystemUser;

/**
 * @InterfaceName UserMapper
 * @Author yangli
 * @Date 2021/7/26 15:02
 * @Description:
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 查询user信息
     * @param username 用户名称
     * @return
     */
    SystemUser findByName(String username);
}
