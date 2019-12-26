package com.example.realm;

import com.example.entity.User;
import com.example.service.AuthService;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:21
 * @desc：
 **/
public class MyAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(MyAuthorizingRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("正在进行授权认证");
        User user = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            logger.error("授权失败 ： 用户没有登录");
            return null;
        }

        SimpleAuthorizationInfo authorizationInfo = null;
        try {
            authorizationInfo = new SimpleAuthorizationInfo();
            //Set<String> roleList = roleService.findRoleByUserId(user.getUid());
            //authorizationInfo.addRoles(roleList);
            Set<String> authList = authService.findAuthByUserId(user.getUid());
            authorizationInfo.addStringPermissions(authList);
        } catch (Exception e) {
            logger.error("授权失败" + e.toString());
            return null;
        }
        return authorizationInfo;
    }

    // 获取登陆信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        String username = authToken.getUsername();
        Optional<User> optional = userService.getUserByUserName(username);
        if (!optional.isPresent()) {
            logger.error("账号不存在");
            return null;
        }
        User user = optional.get();
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(username+User.SALT), getName());
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
                ByteSource.Util.bytes(username + User.SALT), 2).toHex();
        return md5Pwd;
    }

    public static void main(String[] args) {
        String ps = MD5Pwd("root", "123456");
        System.out.println(ps);
    }
}
