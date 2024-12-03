package org.example.provider;

import org.example.RedisClient;

import java.util.UUID;

public class RedisLockProvider implements LockProvider {
    private final RedisClient redisClient;
    public RedisLockProvider(RedisClient redisClient)
    {
        this.redisClient = redisClient;
    }
    @Override
    public boolean lock(String key, UUID secretValue) {
         return redisClient.setIfNotExist(key, secretValue);
    }

    @Override
    public boolean unlock(String key, UUID currentSecretValue) {
        return redisClient.deleteIfValueSame(key, currentSecretValue);
    }
}
