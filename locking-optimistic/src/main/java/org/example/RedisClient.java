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
        String uniqueId = UUID.randomUUID().toString(); // Generate a unique ID for this lock
        SetParams params = new SetParams().nx().px(2000);
        String response = jedis.set(key, secretValue.toString(), params);

        if ("OK".equals(response)) {
            System.out.println("Lock acquired with ID: " + uniqueId);
            return true; // Lock acquired successfully
        }
       return false;
    }

    public boolean deleteIfValueSame(String key, UUID secretValue)
    {
        String luaScript =
                "if redis.call('GET', KEYS[1]) == ARGV[1] then " +
                        "    return redis.call('DEL', KEYS[1]) " +
                        "else " +
                        "    return 0 " +
                        "end";

        Object result = jedis.eval(luaScript, 1, key, secretValue.toString());
        if (result.equals(1L)) {
            System.out.println("Lock released: " + secretValue);
            return true; // Lock released successfully
        }
        return false;
    }
}
