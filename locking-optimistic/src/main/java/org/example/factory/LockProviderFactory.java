package org.example.factory;

import org.example.RedisClient;
import org.example.provider.FaultyLockProvider;
import org.example.provider.InMemoryLockProvider;
import org.example.provider.LockProvider;
import org.example.provider.RedisLockProvider;

public class LockProviderFactory {

    public static final String IN_MEMORY = "InMemory";
    public static final String REDIS = "Redis";

    public static LockProvider getLockProvider(String lockProviderType)
    {
        LockProvider lockProvider;
        switch (lockProviderType){
            case IN_MEMORY:
                lockProvider = new InMemoryLockProvider();
                break;
            case REDIS:
                lockProvider = new RedisLockProvider(new RedisClient());
                break;
            default:
                lockProvider = new FaultyLockProvider();
        }
        return lockProvider;
    }

}
