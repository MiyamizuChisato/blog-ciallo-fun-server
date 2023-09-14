package fun.ciallo.blog.core.category.controller;

import cn.hutool.core.bean.BeanUtil;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.common.Status;
import fun.ciallo.blog.common.Token;
import fun.ciallo.blog.core.archive.service.ArchiveService;
import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.category.dto.CategorySaveDto;
import fun.ciallo.blog.core.category.entity.Category;
import fun.ciallo.blog.core.category.service.CategoryService;
import fun.ciallo.blog.utils.AssertUtils;
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
    private ArchiveService archiveService;
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

    @Token(admin = true)
    @PutMapping("/update")
    public void update(@RequestBody CategoryDto categoryDto) {
        Category category = BeanUtil.copyProperties(categoryDto, Category.class);
        categoryService.updateById(category);
        cacheUtils.delete(RedisConstants.CATEGORIES);
        cacheUtils.delete(RedisConstants.CATEGORY_MAP);
    }

    @Token(admin = true)
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable int id) {
        AssertUtils.notTrue(archiveService.existsByCategory(id), Status.CATEGORY_USED);
        categoryService.removeById(id);
    }
}
