package com.boot.common.shrio;

import com.boot.common.config.ApplicationContextRegister;
import com.boot.common.utils.ShiroUtils;
import com.boot.system.dao.SysUserDao;
import com.boot.system.domain.SysUserDo;
import com.boot.system.service.SysMenuService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Long userId = ShiroUtils.getUserId();
        SysMenuService sysMenuService = ApplicationContextRegister.getBean(SysMenuService.class);
        Set<String> perms = sysMenuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("userName", userName);
        String password = new String((char[]) token.getCredentials());

        SysUserDao userMapper = ApplicationContextRegister.getBean(SysUserDao.class);
        // 查询用户信息
        List<SysUserDo> userList = userMapper.list(map);

        // 账号不存在
        if (userList.size() == 0) {
            throw new UnknownAccountException("账号不存在");
        }
        SysUserDo user = userList.get(0);
        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo( user, password, getName());
        return info;
    }

}
