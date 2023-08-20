package fun.ciallo.blog.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

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
        try {
            return JWTUtil.verify(token, SECRET.getBytes());
        } catch (Exception e) {
            return false;
        }
    }

    public static int getUserIdByToken(String token) {
        return Integer.parseInt(JWTUtil.parseToken(token).getPayload("userId").toString());
    }
}
