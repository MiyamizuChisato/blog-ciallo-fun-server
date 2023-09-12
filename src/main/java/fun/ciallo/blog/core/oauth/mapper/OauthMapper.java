package fun.ciallo.blog.core.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.ciallo.blog.core.oauth.entity.Oauth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthMapper extends BaseMapper<Oauth> {
}
