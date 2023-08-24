package fun.ciallo.blog.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.blog.entity.Archive;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.ArchiveService;
import fun.ciallo.blog.vo.ArchiveVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;

@RestController
@RequestMapping("/archive")
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Open(HttpMethod.GET)
    @GetMapping("/page")
    public IPage<ArchiveVo> page(long current, long size, Integer category) {
        String archivePageJson = stringRedisTemplate.opsForValue().get("archives:" + current + ":" + size);
        if (StringUtils.hasLength(archivePageJson)) {
            return JSONObject.parseObject(archivePageJson, new TypeReference<Page<ArchiveVo>>() {
            });
        }
        Page<Archive> parmaPage = new Page<>(current, size);
        IPage<ArchiveVo> page = archiveService.getArchiveByPage(parmaPage, category);
        stringRedisTemplate.opsForValue().set(
                "archives:" + current + ":" + size,
                JSON.toJSONString(page),
                Duration.ofMinutes(20L)
        );
        return page;
    }

    @Open(HttpMethod.GET)
    @GetMapping("/{id}")
    public ArchiveVo get(@PathVariable int id) {
        String archiveJson = stringRedisTemplate.opsForValue().get("archive:" + id);
        if (StringUtils.hasLength(archiveJson)) {
            return JSONObject.parseObject(archiveJson, ArchiveVo.class);
        }
        ArchiveVo archiveVo = archiveService.getArchiveById(id);
        stringRedisTemplate.opsForValue().set(
                "archive:" + id,
                JSON.toJSONString(archiveVo),
                Duration.ofMinutes(20L)
        );
        return archiveVo;
    }
}
