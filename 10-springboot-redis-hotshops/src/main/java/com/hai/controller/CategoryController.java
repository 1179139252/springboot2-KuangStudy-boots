package com.hai.controller;


import com.hai.common.constant.CategoryConstant;
import com.hai.common.result.ResultCodeEnum;
import com.hai.common.validator.KAssert;
import com.hai.config.KsdRedisBloomConfiguration;
import com.hai.exception.ValidatorException;
import com.hai.pojo.Category;
import com.hai.service.CategoryServiceImpl;
import com.hai.utils.json.JsonUtil;

import io.rebloom.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class CategoryController {

    //    定义日志
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    Client rebloomClient;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    RedisTemplate redisTemplate;

    //    根据id查询
    @PostMapping("/category/list")
    public List<Category> getAllCategories(@RequestParam("id") int cid) {


        KAssert.isEmpty(cid, ResultCodeEnum.NICK_NAME_CHECK);
        // 1：进行布隆过滤 bf.madd redis:bloom:category “1” "2" "3" "4" "5"
        // 2: 进行布隆过滤 bf.mexists redis:bloom:category “1”
        boolean[] booleans = rebloomClient.existsMulti("redis:bloom:category", cid + "");
        // 3：如果你的cid没有在布隆器中，说明你数据不存在，直接返回
        if (!booleans[0]) {
            throw new ValidatorException(401, "分类不存在");
        }

        // BloomFilter的认识
        // 用了Redis缓存就真的不会进入数据库了吗？
        List<Category> categoryList = new ArrayList<>();
        String categories = (String) redisTemplate.opsForValue().get("subcid:" + cid);
        if (StringUtils.isEmpty(categories)) {
            log.info("db去查询了........");
            categoryList = categoryService.findCategroies(cid);
            if (CollectionUtils.isEmpty(categoryList)) {

                redisTemplate.opsForValue().set("subcid:" + cid, JsonUtil.obj2String(categoryList), 5 * 60, TimeUnit.SECONDS);
            } else {

                redisTemplate.opsForValue().set("subcid:" + cid, JsonUtil.obj2String(categoryList));
            }
        } else {
            categoryList = JsonUtil.string2Obj(categories, List.class, Category.class);
        }
        return categoryList;
    }














//
//        KAssert.isEmpty(id, ResultCodeEnum.NICK_NAME_CHECK);
////        拿到redis对象
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//
//        String category = (String) valueOperations.get(CategoryConstant.CATEGOR_SET_KEY + id);
//
//        List<Category> catelist = new ArrayList<>();
//
////        判断查询是否为空
//        if (StringUtils.isEmpty(category)) {
//            log.info("进入数据库查询。。。");
//
//            catelist = categoryService.findCategroies(id);
//
////            判断数据库是否存在
//            if (CollectionUtils.isEmpty(catelist)) {
//                log.warn("数据库不存在...");
////                放入一条空数据到 redis 并设置过期时间
//                valueOperations.set(CategoryConstant.CATEGOR_SET_KEY + id, JsonUtils.objectToJson(catelist));
//
//            } else {
//
//                valueOperations.set(CategoryConstant.CATEGOR_SET_KEY + id, JsonUtils.objectToJson(catelist));
//            }
//        }else {
//            catelist = JsonUtil.string2Obj(category, List.class, Category.class);
//
//        }
//        return catelist;

    }
