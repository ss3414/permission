package com.common.util;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWT {

    /* 生成token */
    public static String sign(String name, String password, Integer expire) { /* expire单位：秒 */
        try {
            Date date = new Date(System.currentTimeMillis() + expire.longValue() * 1000);
            Algorithm algorithm = Algorithm.HMAC256(password); /* HS256 */
            return com.auth0.jwt.JWT.create()
                    .withClaim("name", name)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 校验token */
    public static boolean verify(String token, String name, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                    .withClaim("name", name)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* 从token中获取name */
    public static String getName(String token) {
        try {
            DecodedJWT decodedJWT = com.auth0.jwt.JWT.decode(token);
            return decodedJWT.getClaim("name").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
