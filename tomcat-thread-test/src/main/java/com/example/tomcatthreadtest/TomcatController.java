package com.example.tomcatthreadtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

    @Slf4j
    @RestController
    @RequestMapping("/")
    public class TomcatController {

        @GetMapping
        public void tomcatTest() throws InterruptedException {
            log.info("Controller Received");
            Thread.sleep(5000);
        }
    }
