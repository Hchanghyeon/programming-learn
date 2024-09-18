package com.lock.namedlock.common.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcTemplateLockRepository implements LockRepository {

    private static final String ACQUIRE_QUERY = "SELECT GET_LOCK(:key, :timeout)";
    private static final String RELEASE_QUERY = "SELECT RELEASE_LOCK(:key)";

    private static final Long MAX_CATCH_TIME = 5L;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void lockAcquired(String key) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("timeout", MAX_CATCH_TIME);

        Integer result = jdbcTemplate.queryForObject(ACQUIRE_QUERY, params, Integer.class);
        checkResult(result, key, "GETLOCK");
    }

    @Override
    public void lockRelease(String key) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);

        Integer result = jdbcTemplate.queryForObject(RELEASE_QUERY, params, Integer.class);
        checkResult(result, key, "RELEASELOCK");
    }

    private void checkResult(final Integer result, final String key, final String lockType) {
        if (result != 1) {
            log.warn("{} error type : {}", key, lockType);
        }
    }
}
