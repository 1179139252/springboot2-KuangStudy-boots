package com.hai.controller;

import com.hai.common.result.R;
import com.hai.constants.RedsConstants;
import com.hai.pojo.Content;
import com.hai.utlis.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class ContentController {

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("saveContent")
    public Content saveContent(Content content) throws IllegalAccessException {
        // 1: 先把用户注册到DB中
        //contentService.saveOrUpdate(content);
        // 2： 然后查询最新的用户信息放入到redis的hash重
        //Content cont = contentService.getById(content.getId());
        // 1: 将对象转换map
        Map<String, Object> map = ObjectUtils.objectToMap(content);
        // 3: 准备用存入的key，将用户信息存入到redis的hash中
        String key = RedsConstants.INSERT_HASH_CONTENT + content.getId();
        redisTemplate.opsForHash().putAll(key, map);
        // 4: 设置key的失效时间一个月
        redisTemplate.expire(key, 30, TimeUnit.DAYS);
        return content;
    }
}
