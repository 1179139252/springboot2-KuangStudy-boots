package com.hai.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 关注列表
 *
 * @param
 * @author merry
 * @return
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/26 18:20
 */


@Service
public class FollowListService {

    //    关注列表
    private final static String FOLLOWEE = "foollowee:set:";
    //    粉丝列表
    private final static String FOLLOWER = "foollower:set:";

    @Autowired
    RedisTemplate redisTemplate;


    //   添加关注
    public void addFollow(String followee, String follower) {

//        关注列表
        redisTemplate.opsForSet().add(FOLLOWEE + followee, follower);
//        粉丝列表
        redisTemplate.opsForSet().add(FOLLOWER + follower, followee);

    }

//   查询关注
    public Set selectFoollowee(String userId) {

        Set<String> members = redisTemplate.opsForSet().members(FOLLOWEE + userId);

        return members;
    }

    //   查询粉丝
    public Set selectFoollower(String userId) {

        Set<String> members = redisTemplate.opsForSet().members(FOLLOWER + userId);

        return members;
    }

}
