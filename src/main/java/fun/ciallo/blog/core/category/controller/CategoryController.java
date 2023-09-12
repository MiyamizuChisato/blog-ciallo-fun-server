package fun.ciallo.blog.core.category.controller;

import cn.hutool.core.bean.BeanUtil;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.category.dto.CategorySaveDto;
import fun.ciallo.blog.core.category.entity.Category;
import fun.ciallo.blog.core.category.service.CategoryService;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CacheUtils cacheUtils;

    @GetMapping("/list")
    public List<CategoryDto> list() {
        return categoryService.loadCategoryDtoList();
    }

    @PostMapping("/save")
    public void saveCategory(@RequestBody CategorySaveDto categorySaveDto) {
        Category category = BeanUtil.copyProperties(categorySaveDto, Category.class);
        categoryService.save(category);
        cacheUtils.delete(RedisConstants.CATEGORIES);
        cacheUtils.delete(RedisConstants.CATEGORY_MAP);
    }
}
