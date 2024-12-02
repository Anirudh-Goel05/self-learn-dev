package org.example;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLockProvider implements LockProvider{
    ConcurrentHashMap<String, UUID> lockMap;

    public InMemoryLockProvider()
    {
        this.lockMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean lock(String key, UUID secretValue) {
        synchronized (this) {
            if (lockMap.containsKey(key)) {
                return false;
            } else {
                lockMap.put(key, secretValue);
                return true;
            }
        }
    }

    @Override
    public boolean unlock(String key, UUID currentSecretValue) {
        synchronized (this)
        {
            if (lockMap.containsKey(key) && lockMap.get(key) == currentSecretValue) {
                lockMap.remove(key);
                return true;
            } else {
                // do nothing
            }
            return false;
        }
    }
}
