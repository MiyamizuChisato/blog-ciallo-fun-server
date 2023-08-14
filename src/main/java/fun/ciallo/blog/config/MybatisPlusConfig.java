package fun.ciallo.blog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "fun.ciallo.blog.mapper")
public class MybatisPlusConfig {

}
