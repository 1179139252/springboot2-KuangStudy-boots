package com.hai.config;

import io.rebloom.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/10 10:42
 */
@Configuration
public class KsdRedisBloomConfiguration {

    private static final Logger log = LoggerFactory.getLogger(KsdRedisBloomConfiguration.class);

    // host地址
    @Value("${redis.bloom.host}")
    private String host;
    // host地址
    @Value("${redis.bloom.password}")
    private String password;
    // 端口
    @Value("${redis.bloom.port}")
    private Integer port;
    // 基数
    @Value("${redis.bloom.capacity}")
    private Integer capacity;
    // 错误率
    @Value("${redis.bloom.rate}")
    private Double rate;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxWaitMillis(5 * 1000);
        JedisPool jp = new JedisPool(poolConfig, host, port,
                3 * 1000, password, 0);
        return jp;
    }

    @Bean
    public Client rebloomClient(JedisPool pool) {
        // 1: 初始化布隆过滤器的client对象
        Client client = new Client(pool);
        try {
            // 初始化bloomfilter的容器错率
            client.createFilter("redis:bloom:filter", capacity, rate);
        } catch (Exception ex) {
            log.info("bloom过滤器已经存在，异常信息是：{}", ex.getMessage());
        }
        return client;
    }
}