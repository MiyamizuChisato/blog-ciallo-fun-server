package fun.ciallo.blog.core.message.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.core.message.dto.MessageDto;
import fun.ciallo.blog.core.message.dto.MessageQueryDto;
import fun.ciallo.blog.core.message.entity.Message;
import fun.ciallo.blog.core.message.mapper.MessageMapper;
import fun.ciallo.blog.core.message.service.MessageService;
import fun.ciallo.blog.core.user.service.UserService;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Resource
    private UserService userService;
    @Resource
    private CacheUtils cacheUtils;

    @Override
    public Page<MessageDto> queryMessageDtoByPage(Page<Message> parmaPage, MessageQueryDto messageQueryDto) {
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isBlankIfStr(messageQueryDto.getContent())) {
            lambdaQueryWrapper.like(Message::getContent, messageQueryDto.getContent());
        }
        if (messageQueryDto.getCreateUser() != null) {
            lambdaQueryWrapper.eq(Message::getCreateUser, messageQueryDto.getCreateUser());
        }
        parmaPage = this.page(parmaPage, lambdaQueryWrapper);
        return this.buildMessageDtoPage(parmaPage);
    }

    @Override
    public Page<MessageDto> loadMessageDtoByPage(Page<Message> parmaPage) {
        return cacheUtils.pageWithFunction(RedisConstants.MESSAGE_PAGE, parmaPage, this::getMessageDtoByPage, RedisConstants.TTL_LONG);
    }

    @Override
    public Page<MessageDto> getMessageDtoByPage(Page<Message> parmaPage) {
        parmaPage = this.page(parmaPage);
        return this.buildMessageDtoPage(parmaPage);
    }

    private Page<MessageDto> buildMessageDtoPage(Page<Message> parmaPage) {
        if (parmaPage.getRecords().isEmpty()) {
            return null;
        }
        Page<MessageDto> page = new Page<>();
        BeanUtil.copyProperties(parmaPage, page);
        List<MessageDto> messageDtoList = parmaPage.getRecords().stream().map(this::formatMessage).toList();
        page.setRecords(messageDtoList);
        return page;
    }

    private MessageDto formatMessage(Message message) {
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(message, messageDto);
        messageDto.setCreateUser(userService.loadUserDtoById(message.getCreateUser()));
        messageDto.setReplyUser(userService.loadUserDtoById(message.getReplyUser()));
        return messageDto;
    }
}
