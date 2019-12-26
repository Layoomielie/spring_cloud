package com.example.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/7/17 14:54
 * @desc：
 **/
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String)principalCollection.getPrimaryPrincipal();

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
        String password = "123456";

        //与-zhanghongjian加密后的盐值密码
        String saltPassword = "f36d29fdd4e1d2e65d0065d9790ab57d";
        if (userName == null||!userName.equals("admin")) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(password)) {
            throw new AccountException("密码不正确");
        }
        //hashedCredentialsMatcher
        //HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        //MessageDigestPasswordEncoder md5 = new MessageDigestPasswordEncoder("MD5");
        // return new SimpleAuthenticationInfo(userName, password, getName());
        // 这里返回盐值加密认证
        System.out.println(userName);
        return new SimpleAuthenticationInfo(userName, saltPassword, ByteSource.Util.bytes(userName + "-zhanghongjian"), getName());
    }

    /**
     * @Author: 张鸿建
     * @Date: 2019/12/4
     * @Desc: md5加密算法  需要存储数据库的密码是经过两次加密的，然后取出和username加盐值对比
     */
    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "-zhanghongjian"), 2).toHex();
        return md5Pwd;
    }

}