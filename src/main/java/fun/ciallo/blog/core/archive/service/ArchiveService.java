package fun.ciallo.blog.core.archive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.core.archive.dto.ArchiveDto;
import fun.ciallo.blog.core.archive.dto.ArchiveQueryDto;
import fun.ciallo.blog.core.archive.entity.Archive;

public interface ArchiveService extends IService<Archive> {
    ArchiveDto loadArchiveDtoById(int id);

    ArchiveDto getArchiveDtoById(int id);

    Page<ArchiveDto> loadArchiveDtoByPage(Page<Archive> parmaPage);

    Page<ArchiveDto> getArchiveDtoByPage(Page<Archive> parmaPage);

    Page<ArchiveDto> queryArchiveDtoByPage(Page<Archive> parmaPage, ArchiveQueryDto archiveQueryDto);

    boolean existsByCategory(int id);
}
