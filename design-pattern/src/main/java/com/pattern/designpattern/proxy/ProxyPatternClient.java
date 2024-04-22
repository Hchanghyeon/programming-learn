package com.pattern.designpattern.proxy;

public class ProxyPatternClient {

    private Subject subject;

    public ProxyPatternClient(final Subject subject) {
        this.subject = subject;
    }

    public void execute(){
        subject.operation();
    }
}
