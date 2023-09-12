package fun.ciallo.blog.core.archive.controller;

import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.common.Status;
import fun.ciallo.blog.common.Token;
import fun.ciallo.blog.common.UserHolder;
import fun.ciallo.blog.core.archive.dto.ArchiveDto;
import fun.ciallo.blog.core.archive.dto.ArchiveQueryDto;
import fun.ciallo.blog.core.archive.dto.ArchiveSaveDto;
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

    @GetMapping("/page/{current}")
    public IPage<ArchiveDto> getArchiveByPage(@PathVariable long current) {
        Page<Archive> parmaPage = new Page<>(current, 5L);
        IPage<ArchiveDto> page = archiveService.loadArchiveDtoByPage(parmaPage);
        AssertUtils.notNull(page, Status.NOT_FOUND);
        return page;
    }

    @GetMapping("/query/{current}")
    public IPage<ArchiveDto> queryArchiveByPage(@PathVariable long current, @RequestBody ArchiveQueryDto archiveQueryDto) {
        Page<Archive> parmaPage = new Page<>(current, 5L);
        IPage<ArchiveDto> page = archiveService.queryArchiveDtoByPage(parmaPage, archiveQueryDto);
        AssertUtils.notNull(page, Status.NOT_FOUND);
        return page;
    }

    @Token(admin = true)
    @PostMapping("/save")
    public void saveArchive(ArchiveSaveDto archiveSaveDto) {
        UserDto userDto = UserHolder.get();
        archiveSaveDto.setCreateUser(userDto.getId());
        Archive archive = new Archive();
        BeanUtils.copyProperties(archiveSaveDto, archive);
        String content = fileStorageService.of(archiveSaveDto.getContent()).setPath("markdown/").setObjectType("md").upload().getUrl();
        String image = fileStorageService.of(archiveSaveDto.getImage()).setPath("image/").setObjectType("image").upload().getUrl();
        archive.setImage(image);
        archive.setContent(content);
        archiveService.save(archive);
        cacheUtils.batchDelete(RedisConstants.ARCHIVE_PAGE);
    }
}
