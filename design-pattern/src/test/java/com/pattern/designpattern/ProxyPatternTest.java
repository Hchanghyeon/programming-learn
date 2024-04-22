package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.proxy.ProxyPatternClient;
import com.pattern.designpattern.proxy.RealSubject;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient((realSubject));
        client.execute();
        client.execute();
        client.execute();
    }
}
