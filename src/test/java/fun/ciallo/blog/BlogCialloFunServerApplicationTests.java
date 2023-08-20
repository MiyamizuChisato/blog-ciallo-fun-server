package fun.ciallo.blog;

import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogCialloFunServerApplicationTests {

    @Test
    void contextLoads() {
        String userId = JWTUtil.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjMsImlhdCI6MTY5MjQwNjMzNTc3NH0.Hjvlp-qo0t9tAVRKfKvwVvh_hnDNAwpAcXz_wkGb0bE")
                .getPayload("userId").toString();
        System.out.println(Integer.parseInt(userId));
    }

}
