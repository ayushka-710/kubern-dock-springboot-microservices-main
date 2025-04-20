package com.edw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.UnifiedJedis;

/**
 *
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

	// Redis connection test
        UnifiedJedis jedis = new UnifiedJedis("redis://192.168.0.107:6379");

        String pong = jedis.ping();
        System.out.println("Connected to Redis: " + pong);  // should print "PONG"

        jedis.set("foo", "bar");
        System.out.println("Value of 'foo': " + jedis.get("foo"));

        jedis.close();
    }
}
