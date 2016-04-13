package com.joelin.demo.microservice.sb.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	RedisConnectionFactory redisFactory;

	@RequestMapping(value = "/hello", produces = "text/plain")
	public String hello(@RequestParam("name") String name) {
		return "hello " + name;
	}

	@RequestMapping(value = "/addRedis", produces = "text/plain")
	public String addRedis(@RequestParam("value") String value) {

		if (value == null || value.trim().equals("")) {
			value = "null";
		}

		RedisTemplate<String, String> temp = new RedisTemplate<String, String>();
		temp.setConnectionFactory(redisFactory);
		temp.setDefaultSerializer(new StringRedisSerializer());
		temp.afterPropertiesSet();
		temp.opsForValue().set("foo", value);

		return "add key:foo ,value:" + value + " in redis";
	}

	@RequestMapping(value = "/listRedis", produces = "text/plain")
	public String listRedis() {

		RedisTemplate<String, String> temp = new RedisTemplate<String, String>();
		temp.setConnectionFactory(redisFactory);
		temp.setDefaultSerializer(new StringRedisSerializer());
		temp.afterPropertiesSet();
		StringBuffer sb = new StringBuffer();
		Set<String> result = temp.keys("*");

		sb.append("keys: {");
		for (String s : result) {
			sb.append(s + ",");
		}
		sb.append("}");
		String foovalue = temp.opsForValue().get("foo");
		sb.append("foo's value{" + foovalue + "}");
		return sb.toString();
	}

	@RequestMapping(value = "/delRedis", produces = "text/plain")
	public String delRedis(@RequestParam("key") String key) {

		if (key == null || key.trim().equals("")) {
			return "key isn't define";
		}

		RedisTemplate<String, String> temp = new RedisTemplate<String, String>();
		temp.setConnectionFactory(redisFactory);
		temp.setDefaultSerializer(new StringRedisSerializer());
		temp.afterPropertiesSet();

		Set<String> result = temp.keys("*");

		if (result.contains(key.trim())) {
			temp.delete(key);
		}else{
			return "key:"+key+"  not found!";
		}

		return "del from redis for key:" + key;
	}

}
