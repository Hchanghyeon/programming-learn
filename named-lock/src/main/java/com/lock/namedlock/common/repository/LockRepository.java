package com.lock.namedlock.common.repository;

public interface LockRepository {

    void lockAcquired(String key);

    void lockRelease(String key);
}
