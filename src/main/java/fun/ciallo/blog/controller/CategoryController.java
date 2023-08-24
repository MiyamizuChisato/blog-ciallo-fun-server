package fun.ciallo.blog.controller;

import cn.hutool.json.JSONUtil;
import fun.ciallo.blog.entity.Category;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.CategoryService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Open(HttpMethod.GET)
    @GetMapping("/list")
    public List<Category> list() {
        String categoriesJson = stringRedisTemplate.opsForValue().get("category:list");
        if (StringUtils.hasLength(categoriesJson)) {
            return JSONUtil.toList(categoriesJson, Category.class);
        }
        List<Category> list = categoryService.list();
        stringRedisTemplate.opsForValue().set("category:list", JSONUtil.toJsonStr(list));
        return list;
    }
}
