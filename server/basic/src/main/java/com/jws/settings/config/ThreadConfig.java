package com.jws.settings.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
//@EnableScheduling
public class ThreadConfig implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int ASYNC_POOL_SIZE = 10;
    executor.setCorePoolSize(ASYNC_POOL_SIZE);
    executor.setMaxPoolSize(ASYNC_POOL_SIZE * 2);
    executor.setThreadNamePrefix("[BASIC]ASYNC-TASK-POOL-");
    executor.initialize();
    return executor;
  }
//
//  @Override
//  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//    scheduler.setPoolSize(SCHEDULER_POOL_SIZE);
//    scheduler.setThreadNamePrefix("BX_SCHEDULER-TASK-POOL-");
//    scheduler.initialize();
//    taskRegistrar.setTaskScheduler(scheduler);
//  }
}
