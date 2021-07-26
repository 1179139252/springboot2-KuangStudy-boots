package com.hai.controller;

import com.hai.pojo.ShopCart;
import com.hai.utlis.ObjectUtils;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class ShopCartController {


    private final static Logger log = LoggerFactory.getLogger(ShopCartController.class);

    private final static String SHOPCARTID = "shopcartid:";

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 跳转到商品首页
     *
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
     * 商品添加
     *
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @ResponseBody
    @PostMapping("/shopcart/add")
    public ShopCart shopcartAdd(ShopCart shopCart) throws IllegalAccessException {
//      购物车id
        String shopKey = SHOPCARTID + shopCart.getUserid();
//        拿到redis对象
        HashOperations hashOperations = redisTemplate.opsForHash();
//        商品的id
        String productid = shopCart.getProductid().toString();

//        判断是否存在，如果存在则数量加1
        Boolean aBoolean = hashOperations.hasKey(shopKey, productid);
        if (aBoolean){

//            拿到里面的商品数量进行修改
            Map<String, Object> shop = (HashMap<String, Object>) hashOperations.get(shopKey, productid);
//            拿到数量进行+1
            int productnum = (int) shop.get("productnum");
//            返回原来的map，再添加
            shop.put("productnum",++productnum);
            hashOperations.put(shopKey,productid,shop);

        }else {

//        封装存入的数据
            hashOperations.put(shopKey,productid,ObjectUtils.objectToMap(shopCart));
        }

        return shopCart;

    }


    /*@ResponseBody
    @PostMapping("/shopcart/add")
    public ShopCart shopcartAdd(ShopCart shopCart) throws IllegalAccessException {
//        定义购物车的key
        String shopCartid = SHOPCARTID + shopCart.getUserid();


        Boolean aBoolean = redisTemplate.opsForHash().getOperations().hasKey(shopCartid);
        log.info("当前的值是否存在：" + aBoolean);
//       判断当前的购物车是否存在
        if (aBoolean) {
            redisTemplate.opsForHash().put(shopCartid, shopCart.getProductid().toString(), ObjectUtils.objectToMap(shopCart));
//            存在则设置30天的过期时间
            redisTemplate.expire(shopCartid, 30, TimeUnit.DAYS);

        } else {

            redisTemplate.opsForHash().put(shopCartid, shopCart.getProductid().toString(), ObjectUtils.objectToMap(shopCart));
        }


        return shopCart;
    }*/


    /**
     * 商品更新
     *
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */
    @ResponseBody
    @PostMapping("/shopcart/update")
    public String shopcartUpdate(ShopCart shopCart) throws IllegalAccessException {
        String shopCartid = SHOPCARTID + shopCart.getUserid();
        Boolean aBoolean = redisTemplate.opsForHash().getOperations().hasKey(shopCartid);
        redisTemplate.opsForHash().put(shopCartid, shopCart.getProductid().toString(), ObjectUtils.objectToMap(shopCart));
//            存在则设置30天的过期时间
        redisTemplate.expire(shopCartid, 30, TimeUnit.DAYS);
        return "shopcart";
    }


    /**
     * 商品删除1
     *
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @PostMapping("/shopcart/del")
    @ResponseBody
    public String shopcartDel(Long userid, Long productid) {
        String shopCartid = SHOPCARTID + userid;
        redisTemplate.opsForHash().delete(shopCartid, productid.toString());
        return "succ";
    }


    /**
     * 商品查询
     *
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/19 18:02
     */

    @ResponseBody
    @PostMapping("/shopcart/list")
    public Map<String, Object> shopcartList(Long userid) {
        String shopCartid = SHOPCARTID + userid;

//       定义总金额
        double totalmoney = 0d;
//   当前购物车数量的值
        Long size = redisTemplate.opsForHash().size(shopCartid);
//        拿到k v键值对
        Map<String, Map<String, Object>> entries = redisTemplate.opsForHash().entries(shopCartid);
//        存放当前购物车的数据
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : entries.entrySet()) {
//            具体商品的数据
            resultList.add(stringMapEntry.getValue());
//            拿到当前商品的数量和金额进行运算
            totalmoney += Integer.parseInt(String.valueOf(stringMapEntry.getValue().get("productnum"))) *
                    Double.parseDouble(String.valueOf(stringMapEntry.getValue().get("productprice")));
        }


        // 6 : 返回购物车的数据信息
        Map<String, Object> map = new HashMap<>();
        // 总计
        map.put("totalPrice", totalmoney);
        // 这个商品的个数
        map.put("totalCount", size);
        // 具体商品的数据
        map.put("resultList", resultList);

        return map;
    }


}
