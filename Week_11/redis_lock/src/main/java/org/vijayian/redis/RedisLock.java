package org.vijayian.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁.
 *
 * @author ViJay
 */
@Component
public class RedisLock {

    private final StringRedisTemplate redisTemplate;

    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * set lockId content PX millisecond NX
     *
     * @param lockId
     * @param expiresTime
     * @return
     */
    public boolean getLock(String lockId, long expiresTime) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock", expiresTime, TimeUnit.MILLISECONDS);
        return success != null && success;
    }

    /**
     * 释放锁
     *
     * @param lockId
     */
    public void releaseLock(String lockId){
        redisTemplate.delete(lockId);
    }
}
