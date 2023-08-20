package fun.ciallo.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.entity.Category;

import java.util.Map;

public interface CategoryService extends IService<Category> {
    Map<Integer, String> categoryMap();
}
