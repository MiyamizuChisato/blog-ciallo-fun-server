package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.Category;
import fun.ciallo.blog.mapper.CategoryMapper;
import fun.ciallo.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public Map<Integer, String> categoryMap() {
        List<Category> categories = list();
        Map<Integer, String> map = new HashMap<>();
        categories.forEach(category -> {
            map.put(category.getId(), category.getRemark());
        });
        return map;
    }
}
