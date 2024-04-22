package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.proxy.CacheProxy;
import com.pattern.designpattern.proxy.ProxyPatternClient;
import com.pattern.designpattern.proxy.RealSubject;
import com.pattern.designpattern.proxy.Subject;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient((realSubject));
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void proxyTest(){
        RealSubject realSubject = new RealSubject();
        Subject subject = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(subject);
        client.execute();
        client.execute();
        client.execute();
    }
}
