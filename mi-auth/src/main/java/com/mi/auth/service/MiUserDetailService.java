package com.mi.auth.service;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mi.auth.manager.UserManager;
import com.mi.entity.auth.AuthUser;
import com.mi.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName MiUserDetailService
 * @Author yangli
 * @Date 2021/7/14 21:26
 * @Description: 校验用户名密码的类  需要实现UserDetailsService
 */
@Service
public class MiUserDetailService implements UserDetailsService {

    /**
     * 注入密码加密对象
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 校验用户名密码 实体类
        SystemUser user = this.userManager.findByName(username);
        if (ObjectUtils.isNotEmpty(user)) {
            String permissions = this.userManager.findUserPermissions(user.getUsername());
            boolean notLocked = false;

            if (StringUtils.equals(SystemUser.STATUS_VALID, user.getStatus()))
                notLocked = true;
            AuthUser authUser = new AuthUser(user.getUsername(), user.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            return transSystemUserToAuthUser(authUser,user);
        } else {
            throw new UsernameNotFoundException("");
        }
    }


    private AuthUser transSystemUserToAuthUser(AuthUser authUser, SystemUser systemUser) {
        authUser.setAvatar(systemUser.getAvatar());
        authUser.setDeptId(systemUser.getDeptId());
        authUser.setDeptName(systemUser.getDeptName());
        authUser.setEmail(systemUser.getEmail());
        authUser.setMobile(systemUser.getMobile());
        authUser.setRoleId(systemUser.getRoleId());
        authUser.setRoleName(systemUser.getRoleName());
        authUser.setSex(systemUser.getSex());
        authUser.setUserId(systemUser.getUserId());
        authUser.setLastLoginTime(systemUser.getLastLoginTime());
        authUser.setDescription(systemUser.getDescription());
        authUser.setStatus(systemUser.getStatus());
        return authUser;
    }
}
