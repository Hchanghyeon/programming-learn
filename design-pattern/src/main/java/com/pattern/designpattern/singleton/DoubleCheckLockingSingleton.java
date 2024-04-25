package com.pattern.designpattern.singleton;

public class DoubleCheckLockingSingleton {

    private volatile static DoubleCheckLockingSingleton doubleCheckLockingSingleton;

    private DoubleCheckLockingSingleton(){}

    public static DoubleCheckLockingSingleton getInstance(){
        if(doubleCheckLockingSingleton == null){
            synchronized (DoubleCheckLockingSingleton.class){
                if(doubleCheckLockingSingleton == null){
                    doubleCheckLockingSingleton = new DoubleCheckLockingSingleton();
                }
            }
        }

        return doubleCheckLockingSingleton;
    }
}
