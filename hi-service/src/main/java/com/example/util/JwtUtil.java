package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/8/7 17:48
 * @desc：
 **/
public class JwtUtil {

    final public static String jwtPassword="44253432";

    public String getToken() {
        String token = "";
        // 使用HS256加密认证
        //  构建header信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Algorithm algorithm = Algorithm.HMAC256(JwtUtil.jwtPassword);
//        token = JWT.create().withAudience(user.getId())
//                .sign(Algorithm.HMAC256(user.getPassword()));
        token = JWT.create().withHeader(map).withClaim("authorization", "author").withIssuer("SERVICE").withSubject("this is node token").withAudience("ali").sign(algorithm);

        return token;
    }
}
