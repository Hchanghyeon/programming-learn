package com.study.async.asyncannotationtest;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {
        Executor executor = new SimpleAsyncTaskExecutor();
        return executor;
    }

    /*
        주의!!!
        - 최초 core 사이즈만큼 동작하다가 더 이상 처리할 수 없을 경우 max 사이즈만큼 스레드가 증가할 것이라고 예상할 수 있지만 그렇지 않음.
        - 내부적으로는 Integer.MAX_VALUE 사이즈의 LinkedBLockingQueue를 생성해서 core 사이즈만큼의 스레드에서 Task를 처리할 수 없을 경우 queue에서 대기
        - queue가 꽉 차게 되면 그때 Max 사이즈만큼 스레드를 생성해서 처리하게 됨

        https://kapentaz.github.io/spring/Spring-ThreadPoolTaskExecutor-%EC%84%A4%EC%A0%95/#
     */
}
