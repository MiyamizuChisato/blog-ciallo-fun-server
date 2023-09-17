package fun.ciallo.blog.core.archive.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.common.Status;
import fun.ciallo.blog.common.Token;
import fun.ciallo.blog.common.UserHolder;
import fun.ciallo.blog.core.archive.dto.ArchiveDto;
import fun.ciallo.blog.core.archive.dto.ArchiveQueryDto;
import fun.ciallo.blog.core.archive.dto.ArchiveSaveDto;
import fun.ciallo.blog.core.archive.dto.ArchiveUpdateDto;
import fun.ciallo.blog.core.archive.entity.Archive;
import fun.ciallo.blog.core.archive.service.ArchiveService;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.utils.AssertUtils;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/archive")
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;
    @Resource
    private FileStorageService fileStorageService;
    @Resource
    private CacheUtils cacheUtils;

    @GetMapping("/{id}")
    public ArchiveDto getArchiveById(@PathVariable int id) {
        ArchiveDto archiveDto = archiveService.loadArchiveDtoById(id);
        AssertUtils.notNull(archiveDto, Status.NOT_FOUND);
        return archiveDto;
    }

    @PostMapping("/page/{current}")
    public Page<ArchiveDto> getArchiveByPage(@PathVariable long current, @RequestBody ArchiveQueryDto archiveQueryDto) {
        Page<Archive> parmaPage = new Page<>(current, 5L);
        Page<ArchiveDto> page;
        if (archiveQueryDto.isQuery()) {
            page = archiveService.queryArchiveDtoByPage(parmaPage, archiveQueryDto);
        } else {
            page = archiveService.loadArchiveDtoByPage(parmaPage);
        }
        return page;
    }

    @Token(admin = true)
    @PostMapping("/save")
    public void save(ArchiveSaveDto archiveSaveDto) {
        UserDto userDto = UserHolder.get();
        Archive archive = new Archive();
        BeanUtils.copyProperties(archiveSaveDto, archive);
        archive.setCreateUser(userDto.getId());
        String content = fileStorageService.of(archiveSaveDto.getContent()).setPath("markdown/").setObjectType("md").upload().getUrl();
        String image = fileStorageService.of(archiveSaveDto.getImage()).setPath("image/").setObjectType("image").upload().getUrl();
        archive.setImage(image);
        archive.setContent(content);
        archiveService.save(archive);
        cacheUtils.batchDelete(RedisConstants.ARCHIVE_PAGE);
    }

    @Token(admin = true)
    @PutMapping("/update")
    public void update(ArchiveUpdateDto archiveUpdateDto) {
        UserDto userDto = UserHolder.get();
        Archive archive = archiveService.getById(archiveUpdateDto.getId());
        AssertUtils.isEquals(userDto.getId(), archive.getCreateUser(), Status.ILLEGAL);
        BeanUtil.copyProperties(archiveUpdateDto, archive);
        if (archiveUpdateDto.getImage() != null) {
            fileStorageService.delete(archive.getImage());
            String image = fileStorageService.of(archiveUpdateDto.getImage()).setPath("image/").setObjectType("image").upload().getUrl();
            archive.setImage(image);
        }
        if (archiveUpdateDto.getContent() != null) {
            fileStorageService.delete(archive.getContent());
            String content = fileStorageService.of(archiveUpdateDto.getContent()).setPath("markdown/").setObjectType("md").upload().getUrl();
            archive.setContent(content);
        }
        archiveService.updateById(archive);
        cacheUtils.batchDelete(RedisConstants.ARCHIVE_PAGE);
        cacheUtils.delete(RedisConstants.ARCHIVE + archive.getId());
    }
}
