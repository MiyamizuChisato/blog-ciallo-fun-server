package fun.ciallo.blog.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET = "AO!SAKURA";

    public static String createToken(Integer id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", id);
        payload.put(JWT.ISSUED_AT, System.currentTimeMillis());
        return JWTUtil.createToken(payload, SECRET.getBytes());
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
        return (Integer) JWTUtil.parseToken(token).getPayload("userId");
    }
}
