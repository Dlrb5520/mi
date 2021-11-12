package com.mi.auth.manager;

import com.mi.auth.mapper.MenuMapper;
import com.mi.auth.mapper.UserMapper;
import com.mi.entity.system.Menu;
import com.mi.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserManager
 * @Author yangli
 * @Date 2021/7/26 15:18
 * @Description:
 */
@Service
public class UserManager {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return this.userMapper.findByName(username);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        List<String> perms = new ArrayList<>();
        for (Menu m: userPermissions){
            perms.add(m.getPerms());
        }

        // userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));

        return StringUtils.join(perms, ",");

    }
}
