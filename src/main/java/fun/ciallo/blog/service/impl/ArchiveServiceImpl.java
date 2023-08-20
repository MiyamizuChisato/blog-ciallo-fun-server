package fun.ciallo.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.Archive;
import fun.ciallo.blog.entity.Category;
import fun.ciallo.blog.mapper.ArchiveMapper;
import fun.ciallo.blog.service.ArchiveService;
import fun.ciallo.blog.service.CategoryService;
import fun.ciallo.blog.vo.ArchiveVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArchiveServiceImpl extends ServiceImpl<ArchiveMapper, Archive> implements ArchiveService {
    @Resource
    private CategoryService categoryService;

    @Override
    public IPage<ArchiveVo> getArchiveByPage(Page<Archive> parmaPage, Integer category) {
        Map<Integer, String> categoryMap = categoryService.categoryMap();
        LambdaQueryWrapper<Archive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (category != null) {
            lambdaQueryWrapper.eq(Archive::getCategory, category);
        }
        lambdaQueryWrapper.orderByDesc(Archive::getUpdateTime);
        parmaPage = this.page(parmaPage, lambdaQueryWrapper);
        List<ArchiveVo> archiveVos = parmaPage.getRecords().stream().map(archive -> {
            ArchiveVo archiveVo = BeanUtil.copyProperties(archive, ArchiveVo.class);
            archiveVo.setCategory(categoryMap.get(archive.getCategory()));
            return archiveVo;
        }).toList();
        IPage<ArchiveVo> resultPage = new Page<>();
        BeanUtil.copyProperties(parmaPage, resultPage);
        resultPage.setRecords(archiveVos);
        return resultPage;
    }

    @Override
    public ArchiveVo getArchiveById(int id) {
        Map<Integer, String> categoryMap = categoryService.categoryMap();
        Archive archive = this.getById(id);
        ArchiveVo archiveVo = BeanUtil.copyProperties(archive, ArchiveVo.class);
        archiveVo.setCategory(categoryMap.get(archive.getCategory()));
        return archiveVo;
    }
}
