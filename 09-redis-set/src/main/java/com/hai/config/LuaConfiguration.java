package com.hai.config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/21 12:01
 */
@SpringBootConfiguration
public class LuaConfiguration {
    /**
     * 将lua脚本的内容加载出来放入到DefaultRedisScript
     * @return
     */
    @Bean
    public DefaultRedisScript<Long> initluascript() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/updateuser.lua")));
        defaultRedisScript.setResultType(Long.class);
        return defaultRedisScript;
    }


    @Bean
    public DefaultRedisScript<Long> iprefresh() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/updateuser.lua")));
        defaultRedisScript.setResultType(Long.class);
        return defaultRedisScript;
    }




    @Bean
    public DefaultRedisScript<Boolean> iplimit() {
        DefaultRedisScript<Boolean> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/iplimit.lua")));
        defaultRedisScript.setResultType(Boolean.class);
        return defaultRedisScript;
    }

}