package fun.ciallo.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.entity.Archive;
import fun.ciallo.blog.vo.ArchiveVo;

public interface ArchiveService extends IService<Archive> {

    IPage<ArchiveVo> getArchiveByPage(Page<Archive> parmaPage, Integer category);

    ArchiveVo getArchiveById(int id);
}
