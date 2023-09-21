package fun.ciallo.blog.core.archive.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.core.archive.dto.ArchiveDto;
import fun.ciallo.blog.core.archive.dto.ArchiveQueryDto;
import fun.ciallo.blog.core.archive.entity.Archive;
import fun.ciallo.blog.core.archive.mapper.ArchiveMapper;
import fun.ciallo.blog.core.archive.service.ArchiveService;
import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.category.service.CategoryService;
import fun.ciallo.blog.core.user.dto.UserArchiveDto;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.core.user.service.UserService;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArchiveServiceImpl extends ServiceImpl<ArchiveMapper, Archive> implements ArchiveService {
    @Resource
    private CacheUtils cacheUtils;
    @Resource
    private CategoryService categoryService;
    @Resource
    private UserService userService;

    @Override
    public ArchiveDto loadArchiveDtoById(int id) {
        return cacheUtils.getWithFunction(RedisConstants.ARCHIVE, id, ArchiveDto.class, this::getArchiveDtoById, RedisConstants.TTL_LONGER);
    }

    @Override
    public ArchiveDto getArchiveDtoById(int id) {
        Archive archive = this.getById(id);
        if (archive == null) {
            return null;
        }
        return this.formatArchive(archive);
    }

    @Override
    public Page<ArchiveDto> loadArchiveDtoByPage(Page<Archive> parmaPage) {
        return cacheUtils.pageWithFunction(RedisConstants.ARCHIVE_PAGE, parmaPage, this::getArchiveDtoByPage, RedisConstants.TTL_LONGER);
    }

    @Override
    public Page<ArchiveDto> getArchiveDtoByPage(Page<Archive> parmaPage) {
        parmaPage = this.page(parmaPage);
        return buildArchiveDtoPage(parmaPage);
    }

    @Override
    public Page<ArchiveDto> queryArchiveDtoByPage(Page<Archive> parmaPage, ArchiveQueryDto archiveQueryDto) {
        LambdaQueryWrapper<Archive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(archiveQueryDto.getTitle())) {
            lambdaQueryWrapper.like(Archive::getTitle, archiveQueryDto.getTitle());
        }
        if (archiveQueryDto.getCategory() != null) {
            lambdaQueryWrapper.eq(Archive::getCategory, archiveQueryDto.getCategory());
        }
        if (archiveQueryDto.getCreateUser() != null) {
            lambdaQueryWrapper.eq(Archive::getCreateUser, archiveQueryDto.getCreateUser());
        }
        parmaPage = this.page(parmaPage, lambdaQueryWrapper);
        return buildArchiveDtoPage(parmaPage);
    }

    @Override
    public boolean existsByCategory(int id) {
        LambdaQueryWrapper<Archive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Archive::getCategory, id);
        return this.exists(lambdaQueryWrapper);
    }

    private Page<ArchiveDto> buildArchiveDtoPage(Page<Archive> parmaPage) {
        Page<ArchiveDto> page = new Page<>();
        BeanUtil.copyProperties(parmaPage, page);
        List<ArchiveDto> archiveDtoList = parmaPage.getRecords().stream().map(this::formatArchive).toList();
        page.setRecords(archiveDtoList);
        return page;
    }

    private ArchiveDto formatArchive(Archive archive) {
        ArchiveDto archiveDto = new ArchiveDto();
        BeanUtils.copyProperties(archive, archiveDto);
        Map<Integer, CategoryDto> categoryDtoMap = categoryService.loadCategoryDtoMap();
        archiveDto.setCategory(categoryDtoMap.get(archive.getCategory()));
        UserDto userDto = userService.getUserDtoById(archive.getCreateUser());
        archiveDto.setCreateUser(BeanUtil.copyProperties(userDto, UserArchiveDto.class));
        return archiveDto;
    }
}
