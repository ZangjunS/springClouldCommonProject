package com.zangs.crow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("redis_provider")
@SpringBootTest(classes = MainRedis01.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    StringRedisTemplate sRedisTemplate;

    @Test
    public void redisC() {
        Object a =  sRedisTemplate.opsForValue().get("a");
        redisTemplate.opsForValue().get("a");
        System.out.println(a);
    }
}
