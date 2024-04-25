package com.pattern.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.singleton.ClassicSingleton;
import com.pattern.designpattern.singleton.DoubleCheckLockingSingleton;
import com.pattern.designpattern.singleton.NormalSingleton;
import com.pattern.designpattern.singleton.Singleton;

public class SingletonPatternTest {

    private int numThreads = 1000;

    @Test
    void classicSingleton() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for(int i = 0; i < numThreads; i++){
            executorService.execute(() -> {
                System.out.println(ClassicSingleton.getInstance());
            });

            countDownLatch.countDown();
        }

        countDownLatch.await();
        executorService.shutdown();
    }

    // 테스트 결과 > 서로 다른 해시 코드 확인(10번 시도 중 1번 정도 실패) 결국 보장해주지 못함
    // com.pattern.designpattern.singleton.ClassicSingleton@7a14510f
    // com.pattern.designpattern.singleton.ClassicSingleton@5ea68bf5

    @Test
    void NormalSingleton() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for(int i = 0; i < numThreads; i++){
            executorService.execute(() -> {
                System.out.println(NormalSingleton.getInstance());
            });

            countDownLatch.countDown();
        }

        countDownLatch.await();
        executorService.shutdown();
    }

    // 테스트 수행 결과 항상 일정하게 나옴

    @Test
    void Singleton() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for(int i = 0; i < numThreads; i++){
            executorService.execute(() -> {
                System.out.println(Singleton.getInstance());
            });

            countDownLatch.countDown();
        }

        countDownLatch.await();
        executorService.shutdown();
    }

    // 테스트 수행 결과 항상 일정하게 나옴


    @Test
    void DoubleCheckLockingSingleton() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for(int i = 0; i < numThreads; i++){
            executorService.execute(() -> {
                System.out.println(DoubleCheckLockingSingleton.getInstance());
            });

            countDownLatch.countDown();
        }

        countDownLatch.await();
        executorService.shutdown();
    }

    // 테스트 수행 결과 항상 일정하게 나옴
}
