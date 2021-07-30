package com.hai.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BlackUserService {
    //    定义黑名单用户标识符
    private static final String USER_BLACK_SET = "user:black:set";

//    定义日志
    private static final Logger log= LoggerFactory.getLogger(BlackUserService.class);
    @Autowired
    RedisTemplate redisTemplate;
    //    黑名单的初始化
//    当构造器被调用的时候执行该方法
    @PostConstruct
    public void initBlackUsers() {

//        默认初始化100条数 数据
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForSet().add(USER_BLACK_SET,"黑名单用户:"+i);
        }
    }
    //添加黑名单
    public Long addBlackUsers(Long userId) {
//        首先判断当前人员是否存在在黑名单
        Boolean member = redisTemplate.opsForSet().isMember(USER_BLACK_SET, userId);
        if (member){
            log.warn("黑名单用户："+userId+"当前的用户已经存在黑名单中,请勿重复添加");
            return 0L;
        }else {
            Long add = redisTemplate.opsForSet().add(USER_BLACK_SET,  userId);
            return add;
        }
    }

//删除黑名单
    public Long removeBlackUsers(Long userId){
//        首先判断当前人员是否存在在黑名单
        Boolean member = redisTemplate.opsForSet().isMember(USER_BLACK_SET, userId);
//        存在则删除
        if (member) {
            Long remove = redisTemplate.opsForSet().remove(USER_BLACK_SET, userId);
            return 1L;
        }else {
            return 0L;
        }
    }

}
