package fun.ciallo.blog.core.file.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.blog.core.file.entity.FileDetail;
import fun.ciallo.blog.core.file.mapper.FileDetailMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileDetailService extends ServiceImpl<FileDetailMapper, FileDetail> implements FileRecorder {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public boolean save(FileInfo info) {
        FileDetail detail = BeanUtil.copyProperties(info, FileDetail.class, "attr");
        if (info.getAttr() != null) {
            detail.setAttr(objectMapper.writeValueAsString(info.getAttr()));
        }
        boolean b = save(detail);
        if (b) {
            info.setId(detail.getId());
        }
        return b;
    }

    @Override
    @SneakyThrows
    public FileInfo getByUrl(String url) {
        FileDetail detail = getOne(new LambdaQueryWrapper<FileDetail>().eq(FileDetail::getUrl, url));
        FileInfo info = BeanUtil.copyProperties(detail, FileInfo.class, "attr");
        if (StrUtil.isNotBlank(detail.getAttr())) {
            info.setAttr(objectMapper.readValue(detail.getAttr(), Dict.class));
        }
        return info;
    }

    @Override
    public boolean delete(String url) {
        return remove(new LambdaQueryWrapper<FileDetail>().eq(FileDetail::getUrl, url));
    }
}
