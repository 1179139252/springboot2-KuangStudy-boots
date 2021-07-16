package com.hai.controller;

import com.hai.common.result.ResultCodeEnum;
import com.hai.exception.ValidatorException;
import com.hai.limit.annotion.AccessLimiter;
import com.hai.utlis.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/21 10:03
 */
@RestController
@Slf4j
public class UserLuaController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    @Qualifier(value = "initluascript")
    private DefaultRedisScript<Long> defaultRedisScript;

    @Autowired
    @Qualifier(value = "iprefresh")
    private DefaultRedisScript<Long> iprefresh;


    @Autowired
    @Qualifier(value = "iplimit")
    private DefaultRedisScript<Boolean> iplimit;


    @PostMapping("/user/lua/update2")
    public Long luaupdateUser2(Integer userid, String nickname) {
        String key = "user:" + userid;
        // 1: 第一次：飞先到缓存中根据key去查找一次，看是否存在
        String olduser = this.stringRedisTemplate.opsForValue().get(key);
        if (olduser == null) {
            // 2:第二次：发送第二次redis请求。不存在就新增
            this.stringRedisTemplate.opsForValue().set(key, nickname);
            return 1L;
        }
        if (nickname.equals(olduser)) {
            log.info("用户对象:{}，无须修改!", key);
        } else {
            log.info("用户对象:{}，修改成功！!", key);
            // 2:第二次：发送第二次redis请求。不存在就新增
            this.stringRedisTemplate.opsForValue().set(key, nickname);
        }
        return 1L;
    }

    @PostMapping("/user/lua/update")
    public Long luaupdateUser(Integer userid, String nickname) {
        String key = "user:" + userid;
        // 1: 设置lua的key
        List<String> keysList = Arrays.asList(key);
        // 2 : execute 参数1：执行的lua脚本的对象  参数2：参数的key列表 参数3：执行lua每个key对应的参数
        Long execute = this.stringRedisTemplate.execute(defaultRedisScript, keysList, nickname);
        return execute;
    }

    @PostMapping("/course/lua/list")
    public String courselist(HttpServletRequest request) {
        // 1: 获取请求的IPD
//        String keyip = IPUtils.getIpAddr(request);
//        log.info(keyip);
        // 2 :设置redis的key
//        List<String> keyList = Arrays.asList("course:api:" + keyip);
        List<String> keyList = Arrays.asList("name");
        // 2 : 设置redis并且调用lua的脚本执行，代表30秒内，最多允许访问10次。
        Long execute = this.stringRedisTemplate.execute(iprefresh, keyList, 1, 10);
        log.info("当前execute:" + execute);
//        if (execute == 0) {
//            log.info("1----->ip:{},请求收到限制", keyip);
//            return ("不要执行太快，请稍后试试看...");
//        }
//        log.info("2----->ip:{},正常访问，返回课程列表", keyip);
//        return "正常访问，返回课程列表"+keyip;
        return null;
    }


//    根据请求来限制

    @GetMapping("/limit/redis")
    public String limitredis(String name) {
        List<String> keyList = Arrays.asList("name"+name);
        Boolean flag = stringRedisTemplate.execute(iplimit, keyList, "2","3");

        if (!flag){

            throw new ValidatorException(ResultCodeEnum.IP_REFRSHRETOFAST);
        }else {
            return "success";
        }

    }

//    使用 注解+lua
    @AccessLimiter(limit = 2,timeout = 5,key = "limitredisAnonotion")
    @GetMapping("/limit/anonotion")
    public String limitredisAnonotion(String name) {
            return "success";
        }

    }
