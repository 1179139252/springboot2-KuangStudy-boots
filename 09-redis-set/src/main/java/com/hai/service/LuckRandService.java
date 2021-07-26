package com.hai.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LuckRandService {


    @Autowired
    RedisTemplate redisTemplate;
    private static final String USER_LUCK="user:luck:set";


//    初始化数据
    @PostConstruct
    public void initDatabase(){
//       定义抽奖的随机人数
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForSet().add(USER_LUCK,i);
        }
    }

    public int luckyStart(){
//        随机抽选一位用户并且返回
        int pop = (int) redisTemplate.opsForSet().pop(USER_LUCK);
//        抽到的用户则进行删除
        Long remove = redisTemplate.opsForSet().remove(USER_LUCK, pop);
        return pop;

    }




}
