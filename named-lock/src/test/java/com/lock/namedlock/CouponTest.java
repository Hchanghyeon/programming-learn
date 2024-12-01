package com.lock.namedlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.lock.namedlock.coupon.CouponService;

class CouponTest {

    CouponService couponService = new CouponService();

    @Test
    void testConcurrency() throws InterruptedException {
        // Given
        int numberOfThreads = 500;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                try {
                    couponService.getCoupon();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        // Then
        Assertions.assertEquals(0, couponService.getCouponAmount());
    }
}
