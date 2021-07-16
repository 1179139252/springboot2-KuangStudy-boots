package com.hai;

import com.hai.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setNickname("阿飞呀");
        user.setAge(1);

        redisTemplate.opsForValue().set("users",user);

    }

}
