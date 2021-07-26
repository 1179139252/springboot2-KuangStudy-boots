package com.hai.controller;

import com.hai.service.FollowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 粉丝查询关注
 */


@RestController
public class FollowListController {

    @Autowired
    FollowListService followListService;


//    关注
    @PostMapping("/usr/fans/add")
    public String addFollow(String followee,String follower){

        followListService.addFollow(followee,follower);

        return "关注成功";
    }

//    查询关注列表

    @PostMapping("/user/fans/followeeSelect")
    public Set selectFollowee(String userId){
        Set set = followListService.selectFoollowee(userId);
        return set;
    }

//    查询粉丝列表

    @PostMapping("/user/fans/followerSelect")
    public Set selectFollower(String userId){
        Set set = followListService.selectFoollower(userId);
        return set;
    }

}
