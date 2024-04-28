package com.study.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.async.asyncannotationtest.AsyncProcess;

@SpringBootTest
class AsyncTest {

    @Autowired
    AsyncProcess asyncProcess;

    @Test
    void asyncPoolTest() throws InterruptedException {
        for(int i = 0 ; i < 1000; i++){
            asyncProcess.init();
        }

        System.out.println(Thread.activeCount());
    }
}
