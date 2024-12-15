package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Boolean setNX(String key, Object value,long ttl) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, ttl, TimeUnit.SECONDS);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public void setValue(String key, Object value, long ttl) {
        redisTemplate.opsForValue().set(key, value,ttl, TimeUnit.SECONDS);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
