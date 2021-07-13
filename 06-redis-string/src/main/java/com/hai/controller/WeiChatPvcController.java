package com.hai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class WeiChatPvcController {

    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping("/detail/page/{contentId}")
    public String pageToDetail(@PathVariable("contentId") String contentId, ModelMap map){

        map.put("contentId",contentId);

        return "/content/detail";
    }


    @ResponseBody
    @PostMapping("/content/viewscount")
    public Long pvcCount(@RequestParam("contentId") String contentId){

        Long increment = redisTemplate.opsForValue().increment("contentId" + contentId);

        return increment;
    }


}
