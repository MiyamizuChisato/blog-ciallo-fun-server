package fun.ciallo.blog.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.blog.common.RedisConstants;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class CacheUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void expire(String key, Duration duration) {
        stringRedisTemplate.expire(key, duration);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void set(String key, Object value, Duration duration) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), duration);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public <R, ID> R getWithFunction(String keyPrefix, ID id, Class<R> clazz, Function<ID, R> fallback, Duration duration) {
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(json)) {
            return JSONUtil.toBean(json, clazz);
        }
        if (json != null) {
            return null;
        }
        R result = fallback.apply(id);
        if (result == null) {
            this.set(key, RedisConstants.BLANK, RedisConstants.TTL_SHORT);
            return null;
        }
        this.set(key, result, duration);
        return result;
    }

    @SneakyThrows
    public <T, R> Page<R> pageWithFunction(String keyPrefix, Page<T> iPage, Function<Page<T>, Page<R>> fallback, Duration duration) {
        String key = keyPrefix + iPage.getCurrent();
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(json)) {
            TypeReference<Page<R>> type = new TypeReference<>() {
            };
            return new ObjectMapper().readValue(json, type);
        }
        if (json != null) {
            return null;
        }
        Page<R> result = fallback.apply(iPage);
        if (result == null) {
            this.set(key, RedisConstants.BLANK, RedisConstants.TTL_SHORT);
            return null;
        }
        this.set(key, result, duration);
        return result;
    }

    public <R> List<R> listWithSupplier(String key, Class<R> clazz, Supplier<List<R>> fallback, Duration duration) {
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(json)) {
            return JSONUtil.parseArray(json).toList(clazz);
        }
        if (json != null) {
            return null;
        }
        List<R> result = fallback.get();
        if (result == null) {
            this.set(key, RedisConstants.BLANK, RedisConstants.TTL_SHORT);
            return null;
        }
        this.set(key, result, duration);
        return result;
    }

    public void batchDelete(String key) {
        Set<String> keys = stringRedisTemplate.keys(key + "*");
        if (keys != null)
            stringRedisTemplate.delete(keys);
    }
}
