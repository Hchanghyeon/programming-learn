package com.study.async.asyncannotationtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncProcess asyncProcess;

    @GetMapping
    public void asyncTest() throws InterruptedException {
        for(int i = 0; i < 100; i++){
            asyncProcess.init();
        }
    }
}
