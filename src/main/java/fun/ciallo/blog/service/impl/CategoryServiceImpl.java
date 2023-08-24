package fun.ciallo.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.Category;
import fun.ciallo.blog.mapper.CategoryMapper;
import fun.ciallo.blog.service.CategoryService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map<Integer, String> categoryMap() {
        String categoryMapJson = stringRedisTemplate.opsForValue().get("category:map");
        if (StringUtils.hasLength(categoryMapJson)) {
            return JSON.parseObject(categoryMapJson, new TypeReference<>() {
            });
        }
        List<Category> categories = list();
        Map<Integer, String> map = new HashMap<>();
        categories.forEach(category -> map.put(category.getId(), category.getRemark()));
        stringRedisTemplate.opsForValue().set("category:map", JSON.toJSONString(map));
        return map;
    }
}
