package org.muieer.java.redis;

import redis.clients.jedis.JedisPool;

public class RedisClientExample {

    public static void main(String[] args) {

        try (
                var pool = new JedisPool();
                var jedis = pool.getResource()) {
            jedis.set("clientName", "Jedis");
            String res = jedis.get("clientName");
            System.out.println(res);
        }
    }
}
