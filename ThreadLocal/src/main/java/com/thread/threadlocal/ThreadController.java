package com.thread.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ThreadController {

    private final ThreadLocalComponent threadLocalComponent;

    @GetMapping
    public void check(){
        threadLocalComponent.checkThreadLocal();
    }

    @GetMapping("/{userName}")
    public void add(@PathVariable String userName){
        threadLocalComponent.addUserName(userName);
    }
}
