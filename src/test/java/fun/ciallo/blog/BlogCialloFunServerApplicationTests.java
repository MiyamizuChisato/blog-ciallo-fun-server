package fun.ciallo.blog;

import cn.hutool.jwt.JWTUtil;
import fun.ciallo.blog.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogCialloFunServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(TokenUtils.createToken(1));
    }

}
