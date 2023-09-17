package fun.ciallo.blog.core.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.core.message.dto.MessageDto;
import fun.ciallo.blog.core.message.dto.MessageQueryDto;
import fun.ciallo.blog.core.message.entity.Message;

public interface MessageService extends IService<Message> {
    Page<MessageDto> queryMessageDtoByPage(Page<Message> parmaPage, MessageQueryDto messageQueryDto);
}
