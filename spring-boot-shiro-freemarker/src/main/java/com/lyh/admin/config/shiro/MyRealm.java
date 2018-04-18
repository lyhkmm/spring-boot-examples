package com.lyh.admin.config.shiro;

import java.util.HashSet;
import java.util.Set;

import com.lyh.admin.common.utils.MD5Utils;
import com.lyh.admin.entity.Permission;
import com.lyh.admin.entity.Role;
import com.lyh.admin.entity.User;
import com.lyh.admin.service.IUserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LYH
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理
 */
@Component
public class MyRealm extends AuthorizingRealm {

    public MyRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);

        //FIXME: 暂时禁用Cache
        setCachingEnabled(false);
    }

    @Autowired
    private IUserService userService;

    /**
     * 权限授权是通过继承AuthorizingRealm抽象类，重载doGetAuthorizationInfo()
     * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行，
     * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。
     * 在这个方法中主要是使用类：SimpleAuthorizationInfo进行角色的添加和权限的添加。
     * @author linyuanhuang
     * 16:27 2018/3/29
     * @return org.apache.shiro.authz.AuthorizationInfo
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User dbUser = userService.findByUserName(user.getUserName());
        Set<String> shiroPermissions = new HashSet<>();
        Set<String> roleSet = new HashSet<String>();
        Set<Role> roles = dbUser.getRoles();
        for (Role role : roles) {
            Set<Permission> resources = role.getResources();
            for (Permission resource : resources) {
                shiroPermissions.add(resource.getSourceKey());

            }
            roleSet.add(role.getRoleKey());
        }
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(shiroPermissions);
        return authorizationInfo;
    }

    /**
     * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法
     * 该方法主要执行以下操作:
         1、检查提交的进行认证的令牌信息;
         2、根据令牌信息从数据源(通常为数据库)中获取用户信息;
         3、对用户信息进行匹配验证;
         4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例;
         5、验证失败则抛出AuthenticationException异常信息。
     * @author linyuanhuang
     * 16:26 2018/3/29
     * @return org.apache.shiro.authc.AuthenticationInfo  
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        User user = userService.findByUserName(username);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        Object credentials = token.getCredentials();
        if (credentials == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        String password = new String((char[]) credentials);
        // 密码错误
        if (!MD5Utils.md5(password).equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (user.getLocked() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

        return info;
    }

}
