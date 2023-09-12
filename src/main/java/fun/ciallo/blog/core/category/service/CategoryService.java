package fun.ciallo.blog.core.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.category.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {
    List<CategoryDto> loadCategoryDtoList();

    List<CategoryDto> getCategoryDtoList();

    Map<Integer, CategoryDto> loadCategoryDtoMap();
    Map<Integer, CategoryDto> getCategoryDtoMap();
}
