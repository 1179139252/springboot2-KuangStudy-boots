package com.hai.controller;

import com.hai.pojo.ShopCart;
import com.hai.utlis.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class ShopCartController {

    private final static String USERIDKEY ="userID:";

    @Autowired
    StringRedisTemplate redisTemplate;


/**
 *
 * 跳转到商品首页
 * @return java.lang.String
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/19 18:01
 */
    @GetMapping("/index")
    public String shopcart() {
        return "shopcart";
    }

    /**
     *
     * 商品添加
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @PostMapping("/shopcart/add")
    public ShopCart shopcartAdd(ShopCart shopCart) throws IllegalAccessException {
 // 1: 定义每个用户的购物车的 key
        String shopcartKey = USERIDKEY + shopCart.getUserid();
        // 2: 获取一个操作hash的数据对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        // 3：判断购物车是否存在
        Boolean hasKey = hashOperations.getOperations().hasKey(shopcartKey);
        // 如果购物车存在
        if (hasKey) {
            Map<String, Object> map = ObjectUtils.objectToMap(shopCart);
            // 做功能增强：你可靠如果是相同的商品的把数量进行递增+1
            // 5：如果不存在，就直接覆盖最新的数据进去&设置过期时间30天
//            Object o = hashOperations.get(shopcartKey, shopCart.getProductid());
            hashOperations.put(shopcartKey, shopCart.getProductid().toString(), map);
            // 6: 设置购物车的过期时间
            this.redisTemplate.expire(shopcartKey, 30, TimeUnit.DAYS);
        } else {
            // 参数1： shopcartKey 这个是redis操作的key，其实就购物车
            // 参数2： shopCart.getProductid().toString() 这个是hash数据结构map的key,也就是商品的id
            // 参数3： map 对应的是具体的商品
            // 一句话：就把指定的shopCart.getProductid()添加到指定的用户的shopcartKey购物车中，具体的详细内容是map
            // 4: 然后把shopCart商品添加到redishash中
            Map<String, Object> map = ObjectUtils.objectToMap(shopCart);
            hashOperations.put(shopcartKey, shopCart.getProductid().toString(), map);
        }
        // 7 :添加成功返回即可
        return shopCart;
    }


    /**
     *
     * 商品更新
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @PostMapping("/shopcart/update")
    public String shopcartUpdate() {
        return "shopcart";
    }



    /**
     *
     * 商品删除
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @PostMapping("/shopcart/del")
    public String shopcartDel() {
        return "shopcart";
    }



    /**
     *
     * 商品查询
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @PostMapping("/shopcart/list")
    public String shopcartList() {
        return "shopcart";
    }



}
