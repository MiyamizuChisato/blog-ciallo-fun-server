package fun.ciallo.blog.core.message.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.common.Status;
import fun.ciallo.blog.common.Token;
import fun.ciallo.blog.common.UserHolder;
import fun.ciallo.blog.core.message.dto.MessageDto;
import fun.ciallo.blog.core.message.dto.MessageQueryDto;
import fun.ciallo.blog.core.message.dto.MessageSaveDto;
import fun.ciallo.blog.core.message.entity.Message;
import fun.ciallo.blog.core.message.service.MessageService;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.utils.AssertUtils;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Valid
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;
    @Resource
    private CacheUtils cacheUtils;

    @PostMapping("/page/{current}")
    public Page<MessageDto> getMessageByPage(@PathVariable long current, @RequestBody MessageQueryDto messageQueryDto) {
        Page<Message> parmaPage = new Page<>(current, 5);
        return messageService.queryMessageDtoByPage(parmaPage, messageQueryDto);
    }

    @Token
    @PostMapping("/save")
    public void save(@RequestBody MessageSaveDto messageSaveDto) {
        UserDto userDto = UserHolder.get();
        Message message = new Message();
        BeanUtils.copyProperties(messageSaveDto, message);
        message.setCreateUser(userDto.getId());
        messageService.save(message);
        cacheUtils.batchDelete(RedisConstants.MESSAGE_PAGE);
    }
}
