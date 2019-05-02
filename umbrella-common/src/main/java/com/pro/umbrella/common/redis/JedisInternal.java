package com.pro.umbrella.common.redis;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

interface JedisInternal {

	void set(String key, String value);

	String get(String key);

	Long del(String key);

	Long del(String... keys);

	Long hset(String key, String field, String value);

	String hget(String key, String field);

	String hmset(String key, Map<String, String> hash);

	List<String> hmget(String key, String... fields);

	Map<String, String> hgetall(String key);

	Long hdel(String key, String... fields);

	String setex(String key, int seconds, String value);

	Boolean exists(String key);

	Long setnx(String key, String value);

	String getSet(String key, String value);

	Long expire(String key, int seconds);

	Long incr(String key);

	Long decr(String key);

	long sadd(String key, String... members);

	Set<String> smembers(String key);

	Long srem(String key, String... member);

	Long ttl(String key);

	void close() throws IOException;

	List<String> mget(String... keys);

}