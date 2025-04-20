/*
Unless explicitly stated otherwise all files in this repository are licensed
under the Apache 2.0 License.

This product includes software developed at Datadog (https://www.datadoghq.com/)
Copyright 2023 Datadog, Inc.
 */
package com.datadog.example.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
	
	//Redis connection test
	// UnifiedJedis jedis = new UnifiedJedis("redis://192.168.0.107:6379");

	// String pong = jedis.ping();
        // System.out.println("Connected to Redis: " + pong);  // should print "PONG"

        // jedis.set("foo", "bar");
        // System.out.println("Value of 'foo': " + jedis.get("foo"));

        // jedis.close();

    }
}
