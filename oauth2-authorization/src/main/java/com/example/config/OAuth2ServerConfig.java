package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:34
 * @desc：
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    //访问客户端密钥
    public static final String CLIENT_SECRET = "123456";
    //访问客户端ID
    public static final String CLIENT_ID = "client_1";
    //鉴权模式
    public static final String GRANT_TYPE[] = {"password", "refresh_token"};

    @Autowired
    AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private UserDetailsService userDetailsService;

    /*@Autowired
    private Oauth2ClientServiceDetail oauth2ClientServiceDetail;*/

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource(name ="jdbcUserDetailsService")
    private UserDetailsService jdbcUserDetailsService;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .realm("oauth2-resources") //code授权添加
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //allow check token
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        /*clients.inMemory()
                .withClient("demoApp")
                .secret(bCryptPasswordEncoder.encode("demoAppSecret"))
                .redirectUris("http://baidu.com")//code授权添加
                .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
                .scopes("all")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);*/
        clients.withClientDetails(clientDetails());

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager)
                //允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        //endpoints.tokenStore()
        endpoints.tokenStore(tokenStore());
        //endpoints.userDetailsService(userDetailsService);
        endpoints.userDetailsService(jdbcUserDetailsService);
        //endpoints.userDetailsService(oauth2ClientServiceDetail);

    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }


}
