package com.dly.auth.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.dly.auth.redis.RedisTools.StringSerializer;
import com.dly.auth.redis.RedisTools.ListSerializer;


import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService<T> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setObject( String uuid, T object, Class<T> type )
    {
        redisTemplate.setKeySerializer( RedisTools.StringSerializer.INSTANCE );
        redisTemplate.setValueSerializer( new Jackson2JsonRedisSerializer<T>(type) );
        redisTemplate.afterPropertiesSet();

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set( uuid, object );
    }

    public void setObject( String uuid, T object, Class<T> type, long longTime, TimeUnit unit)
    {
        redisTemplate.setKeySerializer( StringSerializer.INSTANCE );
        redisTemplate.setValueSerializer( new Jackson2JsonRedisSerializer<T>(type) );
        redisTemplate.afterPropertiesSet();

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(uuid, object, longTime, unit);
    }

    /**
     * 设置过期时间，一天
     * @param uuid
     * @param object
     * @param timeOut
     * @param type
     */
    public void setObjectWithExpireTime( String uuid, T object,Integer timeOut, Class<T> type )
    {
        setObject(uuid, object, type);
        if(timeOut==null){
            timeOut = 86400;
        }
        redisTemplate.expire(uuid, timeOut, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    public T getObject( String uuid, Class<T> type )
    {
        redisTemplate.setKeySerializer( StringSerializer.INSTANCE );
        redisTemplate.setValueSerializer( new Jackson2JsonRedisSerializer<T>(type) );
        redisTemplate.afterPropertiesSet();

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return (T)ops.get( uuid );
    }

    public void setList( String uuid, Object object )
    {
        redisTemplate.setKeySerializer( StringSerializer.INSTANCE );
        redisTemplate.setValueSerializer( ListSerializer.INSTANCE );
        redisTemplate.afterPropertiesSet();

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set( uuid, object );
    }

    @SuppressWarnings("unchecked")
    public Object getList( String uuid )
    {
        redisTemplate.setKeySerializer( StringSerializer.INSTANCE );
        redisTemplate.setValueSerializer( ListSerializer.INSTANCE );
        redisTemplate.afterPropertiesSet();

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return (T)ops.get( uuid );
    }

    public void delObject( String uuid )
    {
        redisTemplate.setKeySerializer( StringSerializer.INSTANCE );
        redisTemplate.afterPropertiesSet();

        redisTemplate.delete( uuid );
    }

    public Boolean setExpire( String uuid, long timeout, TimeUnit unit)
    {
        redisTemplate.setKeySerializer( RedisTools.StringSerializer.INSTANCE );
        redisTemplate.setValueSerializer( RedisTools.ListSerializer.INSTANCE );
        redisTemplate.afterPropertiesSet();
        return redisTemplate.expire(uuid, timeout, unit);
    }
}