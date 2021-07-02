package com.hai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: xuke
 * @time: 2021/6/1 21:32
 */
//@SpringBootConfiguration
public class SyncThreadPoolConfiguration {
//    @Primary
    @Bean(name="threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 1: 创建核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        // 2：线程池维护线程的最大数量，只有在缓存队列满了之后才会申请超过核心线程数的线程
        threadPoolTaskExecutor.setMaxPoolSize(100);
        // 3：缓存队列
        threadPoolTaskExecutor.setQueueCapacity(50);
        // 4：线程的空闲事件，当超过了核心线程数之外的线程在达到指定的空闲时间会被销毁
        threadPoolTaskExecutor.setKeepAliveSeconds(200);
        // 5：异步方法内部线的名称
        threadPoolTaskExecutor.setThreadNamePrefix("Merry-thread-");
        // 6：缓存队列的策略
        /* 当线程的任务缓存队列已满并且线程池中的线程数量已经达到了最大连接数，如果还有任务来就会采取拒绝策略，
         * 通常有四种策略：
         *ThreadPoolExecutor.AbortPolicy：丢弃任务并抛出异常：RejectedExcutionException异常
         *ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常
         *ThreadPoolExecutor.DiscardOldestPolicy: 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         *ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用execute()方法，直到成功。
        * */
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}