package fun.ciallo.blog.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET = "AO!SAKURA";

    public static String createToken(Integer id) {
        return JWT.create()
                .setPayload("userId", id)
                .setIssuedAt(new Date())
                .setSigner(JWTSignerUtil.hs256(SECRET.getBytes()))
                .sign();
    }

    public static boolean verify(String token) {
        byte[] key = SECRET.getBytes();
        try {
            return JWT.of(token).setKey(key).verify();
        } catch (Exception e) {
            return false;
        }
    }

    public static Integer getUserIdByToken(String token) {
        return (Integer) new JWTUtil().parseToken(token).getPayload("userId");
    }
}
