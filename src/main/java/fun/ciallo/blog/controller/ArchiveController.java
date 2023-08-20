package fun.ciallo.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.blog.entity.Archive;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.ArchiveService;
import fun.ciallo.blog.vo.ArchiveVo;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/archive")
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;

    @Open(HttpMethod.GET)
    @GetMapping("/page")
    public IPage<ArchiveVo> page(long current, long size, Integer category) {
        Page<Archive> parmaPage = new Page<>(current, size);
        return archiveService.getArchiveByPage(parmaPage, category);
    }

    @Open(HttpMethod.GET)
    @GetMapping("/{id}")
    public ArchiveVo get(@PathVariable int id) {
        return archiveService.getArchiveById(id);
    }
}
