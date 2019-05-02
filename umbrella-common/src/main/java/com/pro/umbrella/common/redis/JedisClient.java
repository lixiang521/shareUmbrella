package com.pro.umbrella.common.redis;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPool;

/**
 * @author zly  Date: 2016/12/15 Time: 上午11:12
 * @version \$Id$
 *          <p>
 *          must be used as singleton, recommend using spring
 *          </p>
 */
public class JedisClient implements Closeable {

	private final JedisInternal jedisInternal;

	public JedisClient(JedisClusterConfig clusterConfig) {
		jedisInternal = new ClusterAdaptor(clusterConfig);
	}

	public JedisClient(String namespace, JedisClusterConfig clusterConfig) {
		jedisInternal = new ClusterAdaptor(namespace, clusterConfig);
	}

	/**
	 * using classical double-write redis
	 *
	 * @deprecated
	 * @see JedisClient#JedisClient(com.pro.umbrella.common.redis.JedisClusterConfig)
	 *
	 * @param jedisPool       main redis
	 * @param backupJedisPool backup redis
	 */
	@Deprecated
	public JedisClient(JedisPool jedisPool, JedisPool backupJedisPool) {
		jedisInternal = new BackupAdaptor(jedisPool, backupJedisPool);
	}

	/**
	 * Set string value, JedisDataException will be thrown if server return is not 'OK'
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		jedisInternal.set(key, value);
	}

	/**
	 * Get the value of the specified key. If the key does not exist null is returned. If the value
	 * stored at key is not a string an error is returned because GET can only handle string values.
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return jedisInternal.get(key);
	}

	/**
	 * Del specified key. If the key does not exists 0 is returned, else deleted row count will returned
	 *
	 * @param key
	 * @return
	 */
	public Long del(String key) {
		return jedisInternal.del(key);
	}

	/**
	 * Del specified key. If the key does not exists 0 is returned, else deleted row count will returned
	 *
	 * @param keys
	 * @return
	 */
	public Long del(String... keys) {
		return jedisInternal.del(keys);
	}

	/**
	 * Set the specified hash field to the specified value.
	 * <p>
	 * If key does not exist, a new key holding a hash is created.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return If the field already exists, and the HSET just produced an needUpdate of the value, 0 is
	 * returned, otherwise if a new field is created 1 is returned.
	 */
	public Long hset(String key, String field, String value) {
		return jedisInternal.hset(key, field, value);
	}

	/**
	 * Set the respective fields to the respective values. HMSET replaces old values with new values.
	 * <p>
	 * If key does not exist, a new key holding a hash is created.
	 * <p>
	 * <b>Time complexity:</b> O(N) (with N being the number of fields)
	 *
	 * @param key
	 * @param hash
	 * @return Return OK or Exception if hash is empty
	 */
	public String hmset(String key, Map<String, String> hash) {
		return jedisInternal.hmset(key, hash);
	}

	/**
	 * If key holds a hash, retrieve the value associated to the specified field.
	 * <p>
	 * If the field is not found or the key does not exist, a special 'nil' value is returned.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 *
	 * @param key
	 * @param field
	 * @return Bulk reply
	 */
	public String hget(String key, String field) {
		return jedisInternal.hget(key, field);
	}

	/**
	 * Retrieve the values associated to the specified fields.
	 * <p>
	 * If some of the specified fields do not exist, nil values are returned. Non existing keys are
	 * considered like empty hashes.
	 * <p>
	 * <b>Time complexity:</b> O(N) (with N being the number of fields)
	 *
	 * @param key
	 * @param fields
	 * @return Multi Bulk Reply specifically a list of all the values associated with the specified
	 * fields, in the same order of the request.
	 */
	public List<String> hmget(String key, String... fields) {
		return jedisInternal.hmget(key, fields);
	}

	/**
	 * Return all the fields and associated values in a hash.
	 * <p>
	 * <b>Time complexity:</b> O(N), where N is the total number of entries
	 *
	 * @param key
	 * @return All the fields and values contained into a hash.
	 */
	public Map<String, String> hgetall(String key) {
		return jedisInternal.hgetall(key);
	}

	/**
	 * Remove the specified field from an hash stored at key.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 *
	 * @param key
	 * @param fields
	 * @return If the field was present in the hash it is deleted and 1 is returned, otherwise 0 is
	 * returned and no operation is performed.
	 */
	public Long hdel(String key, String... fields) {
		return jedisInternal.hdel(key, fields);
	}

	/**
	 * The command is exactly equivalent to the following group of commands
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return Status code reply
	 */
	public String setex(final String key, final int seconds, final String value) {
		return jedisInternal.setex(key, seconds, value);
	}

	/**
	 * Test if the specified key exists. The command returns "1" if the key exists, otherwise "0" is
	 * returned. Note that even keys set with an empty string as value will return "1". Time
	 * complexity: O(1)
	 *
	 * @param key
	 * @return Boolean reply, true if the key exists, otherwise false
	 */
	public Boolean exists(final String key) {
		return jedisInternal.exists(key);
	}

	/**
	 * SETNX works exactly like {@link #set(String, String) SET} with the only difference that if the
	 * key already exists no operation is performed. SETNX actually means "SET if Not eXists".
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key
	 * @param value
	 * @return Integer reply, specifically: 1 if the key was set 0 if the key was not set
	 */
	public Long setnx(final String key, final String value) {
		return jedisInternal.setnx(key, value);
	}

	/**
	 * GETSET is an atomic set this value and return the old value command. Set key to the string
	 * value and return the old value stored at key. The string can't be longer than 1073741824 bytes
	 * (1 GB).
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key
	 * @param value
	 * @return Bulk reply
	 */
	public String getSet(final String key, final String value) {
		return jedisInternal.getSet(key, value);
	}

	/**
	 * Set a timeout on the specified key. After the timeout the key will be automatically deleted by
	 * the server. A key with an associated timeout is said to be volatile in Redis terminology.
	 * <p>
	 * Voltile keys are stored on disk like the other keys, the timeout is persistent too like all the
	 * other aspects of the dataset. Saving a dataset containing expires and stopping the server does
	 * not stop the flow of time as Redis stores on disk the time when the key will no longer be
	 * available as Unix time, and not the remaining seconds.
	 * <p>
	 * Time complexity: O(1)
	 * @see <a href="http://code.google.com/p/redis/wiki/ExpireCommand">ExpireCommand</a>
	 * @param key
	 * @param seconds
	 * @return Integer reply, specifically: 1: the timeout was set. 0: the timeout was not set since
	 *         the key already has an associated timeout (this may happen only in Redis versions &lt;
	 *         2.1.3, Redis &gt;= 2.1.3 will happily needUpdate the timeout), or the key does not exist.
	 */
	public Long expire(String key, int seconds) {
		return jedisInternal.expire(key, seconds);
	}

	/**
	 * Increment the number stored at key by one. If the key does not exist or contains a value of a
	 * wrong type, set the key to the value of "0" before to perform the increment operation.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are not "integer" types.
	 * Simply the string stored at the key is parsed as a base 10 64 bit signed integer, incremented,
	 * and then converted back as a string.
	 * <p>
	 * Time complexity: O(1)
	 * @see #decr(String)
	 * @param key
	 * @return Integer reply, this commands will reply with the new value of key after the increment.
	 */
	public Long incr(String key) {
		return jedisInternal.incr(key);
	}

	/**
	 * Decrement the number stored at key by one. If the key does not exist or contains a value of a
	 * wrong type, set the key to the value of "0" before to perform the decrement operation.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are not "integer" types.
	 * Simply the string stored at the key is parsed as a base 10 64 bit signed integer, incremented,
	 * and then converted back as a string.
	 * <p>
	 * Time complexity: O(1)
	 * @see #incr(String)
	 * @param key
	 * @return Integer reply, this commands will reply with the new value of key after the increment.
	 */
	public Long decr(String key) {
		return jedisInternal.decr(key);
	}

	/**
	 * Add the specified member to the set value stored at key. If member is already a member of the
	 * set no operation is performed. If key does not exist a new set with the specified member as
	 * sole member is created. If the key exists but does not hold a set value an error is returned.
	 * <p>
	 * Time complexity O(1)
	 * @param key
	 * @param members
	 * @return Integer reply, specifically: 1 if the new element was added 0 if the element was
	 *         already a member of the set
	 */
	public long sadd(String key, String... members) {
		return jedisInternal.sadd(key, members);
	}

	/**
	 * Return all the members (elements) of the set value stored at key.
	 * <p>
	 * Time complexity O(N)
	 * @param key
	 * @return Multi buk reply
	 */
	public Set<String> smembers(String key) {
		return jedisInternal.smembers(key);
	}

	/**
	 * The TTL command returns the remaining time to live in seconds of a key that has an
	 * {@link #expire(String, int) EXPIRE} set. This introspection capability allows a Redis client to
	 * check how many seconds a given key will continue to be part of the dataset.
	 * @param key
	 * @return Integer reply, returns the remaining time to live in seconds of a key that has an
	 *         EXPIRE. In Redis 2.6 or older, if the Key does not exists or does not have an
	 *         associated expire, -1 is returned. In Redis 2.8 or newer, if the Key does not have an
	 *         associated expire, -1 is returned or if the Key does not exists, -2 is returned.
	 */
	public Long ttl(String key) {
		return jedisInternal.ttl(key);
	}

	public List<String> mget(String... keys) {
		return jedisInternal.mget(keys);
	}

	@Override
	public void close() throws IOException {
		jedisInternal.close();
	}

}