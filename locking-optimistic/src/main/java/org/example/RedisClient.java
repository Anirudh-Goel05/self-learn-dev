package org.example;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;

public class RedisClient {
    private final JedisPooled jedis;

    RedisClient() {
        jedis = new JedisPooled("localhost", 6379);
    }

    public String set(String key, String value)
    {
        return jedis.set(key, value);
    }

    public String get(String key)
    {
        return jedis.get(key);
    }

    public boolean setIfNotExist(String key, UUID secretValue)
    {
        SetParams params = new SetParams().nx().px(2000);
        // The above will ensure that set will succeed only if key doesn't exist.
        String response = jedis.set(key, secretValue.toString(), params);

        if ("OK".equals(response)) {
            System.out.println("Lock acquired with ID: " + secretValue);
            return true; // Lock acquired successfully
        }
       return false;
    }

    public boolean deleteIfValueSame(String key, UUID secretValue)
    {
        // Lua script is a redis feature which ensures that the given script executes atomically.
        String luaScript =
                "if redis.call('GET', KEYS[1]) == ARGV[1] then " +
                        "    return redis.call('DEL', KEYS[1]) " +
                        "else " +
                        "    return 0 " +
                        "end";
        // In the above script we allow to delete the key only if the value provided matches the existing value,
        // this ensures that only the client who acquired the lock (and is hence aware of the secret value) can release it
        Object result = jedis.eval(luaScript, 1, key, secretValue.toString());
        if (result.equals(1L)) {
            System.out.println("Lock released: " + secretValue);
            return true; // Lock released successfully
        }
        return false;
    }
}
