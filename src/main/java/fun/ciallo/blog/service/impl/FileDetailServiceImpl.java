package fun.ciallo.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.blog.entity.FileDetail;
import fun.ciallo.blog.service.FileDetailService;
import fun.ciallo.blog.mapper.FileDetailMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Miya
 * @description 针对表【file_detail(文件记录表)】的数据库操作Service实现
 * @createDate 2023-08-21 11:34:55
 */
@Service
public class FileDetailServiceImpl extends ServiceImpl<FileDetailMapper, FileDetail>
        implements FileDetailService, FileRecorder {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public boolean save(FileInfo fileInfo) {
        FileDetail detail = BeanUtil.copyProperties(fileInfo, FileDetail.class, "attr");
        if (fileInfo.getAttr() != null) {
            detail.setAttr(objectMapper.writeValueAsString(fileInfo.getAttr()));
        }
        boolean b = save(detail);
        if (b) {
            fileInfo.setId(detail.getId());
        }
        return b;
    }

    @Override
    @SneakyThrows
    public FileInfo getByUrl(String s) {
        FileDetail detail = getOne(new LambdaQueryWrapper<FileDetail>().eq(FileDetail::getUrl, s));
        FileInfo info = BeanUtil.copyProperties(detail, FileInfo.class, "attr");

        //这是手动获取数据库中的 json 字符串 并转成 附加属性字典，方便使用
        if (StrUtil.isNotBlank(detail.getAttr())) {
            info.setAttr(new ObjectMapper().readValue(detail.getAttr(), Dict.class));
        }
        return info;
    }

    @Override
    public boolean delete(String s) {
        return remove(new LambdaQueryWrapper<FileDetail>().eq(FileDetail::getUrl, s));
    }
}




