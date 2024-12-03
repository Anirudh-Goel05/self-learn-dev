package org.example.provider;

import java.util.UUID;

public class FaultyLockProvider implements LockProvider {
    @Override
    public boolean lock(String key, UUID secretValue) {
        return true;
    }

    @Override
    public boolean unlock(String key, UUID currentSecretValue) {
        return true;
    }
}
