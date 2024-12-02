package org.example;

import java.util.UUID;

public interface LockProvider {
     boolean lock(String key, UUID secretValue);

     boolean unlock(String key, UUID currentSecretValue);
}
