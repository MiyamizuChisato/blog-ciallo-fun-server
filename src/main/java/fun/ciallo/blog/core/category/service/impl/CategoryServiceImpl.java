package fun.ciallo.blog.core.category.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.category.entity.Category;
import fun.ciallo.blog.core.category.mapper.CategoryMapper;
import fun.ciallo.blog.core.category.service.CategoryService;
import fun.ciallo.blog.utils.CacheUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CacheUtils cacheUtils;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<CategoryDto> loadCategoryDtoList() {
        return cacheUtils.listWithSupplier(RedisConstants.CATEGORIES, CategoryDto.class, this::getCategoryDtoList, RedisConstants.TTL_LONGEST);
    }

    @Override
    public List<CategoryDto> getCategoryDtoList() {
        List<Category> categories = this.list();
        return categories.stream().map(category -> BeanUtil.copyProperties(category, CategoryDto.class)).toList();
    }

    @Override
    @SneakyThrows
    public Map<Integer, CategoryDto> loadCategoryDtoMap() {
        String json = cacheUtils.get(RedisConstants.CATEGORY_MAP);
        if (StrUtil.isNotBlank(json)) {
            TypeReference<Map<Integer, CategoryDto>> type = new TypeReference<>() {
            };
            return objectMapper.readValue(json, type);
        } else {
            Map<Integer, CategoryDto> categoryDtoMap = this.getCategoryDtoMap();
            cacheUtils.set(RedisConstants.CATEGORY_MAP, categoryDtoMap, RedisConstants.TTL_LONGEST);
            return categoryDtoMap;
        }
    }

    @Override
    public Map<Integer, CategoryDto> getCategoryDtoMap() {
        List<CategoryDto> categoryDtoList = this.loadCategoryDtoList();
        Map<Integer, CategoryDto> map = new HashMap<>();
        categoryDtoList.forEach(categoryDto -> map.put(categoryDto.getId(), categoryDto));
        return map;
    }
}