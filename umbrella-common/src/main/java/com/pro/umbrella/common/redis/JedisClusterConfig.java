package com.pro.umbrella.common.redis;

import java.util.Objects;

public class JedisClusterConfig {

	private String nodes;

	private String businessName;

	private String systemName;

	private int connectionTimeout = 60000;

	private int soTimeout = 60000;

	private int maxWaitMillis = 2000;

	private int maxTotal = 15;

	private int maxAttempts = 5;

	private int poolMaxIdle = 10;

	private int poolMinIdle = 5;

	private boolean poolTestOnBorrow = true;

	private boolean poolTestOnReturn = true;

	private boolean poolTestWhileIdle = true;

	private int numTestsPerEvictionRun = 10;

	private long timeBetweenEvictionRunsMillis = 60000;

	private boolean check = true;

	private String password;

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public int getPoolMaxIdle() {
		return poolMaxIdle;
	}

	public void setPoolMaxIdle(int poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}

	public int getPoolMinIdle() {
		return poolMinIdle;
	}

	public void setPoolMinIdle(int poolMinIdle) {
		this.poolMinIdle = poolMinIdle;
	}

	public boolean isPoolTestOnBorrow() {
		return poolTestOnBorrow;
	}

	public void setPoolTestOnBorrow(boolean poolTestOnBorrow) {
		this.poolTestOnBorrow = poolTestOnBorrow;
	}

	public boolean isPoolTestOnReturn() {
		return poolTestOnReturn;
	}

	public void setPoolTestOnReturn(boolean poolTestOnReturn) {
		this.poolTestOnReturn = poolTestOnReturn;
	}

	public boolean isPoolTestWhileIdle() {
		return poolTestWhileIdle;
	}

	public void setPoolTestWhileIdle(boolean poolTestWhileIdle) {
		this.poolTestWhileIdle = poolTestWhileIdle;
	}

	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(int maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JedisClusterConfig)) return false;
		JedisClusterConfig that = (JedisClusterConfig) o;
		return connectionTimeout == that.connectionTimeout &&
				soTimeout == that.soTimeout &&
				maxWaitMillis == that.maxWaitMillis &&
				maxTotal == that.maxTotal &&
				maxAttempts == that.maxAttempts &&
				poolMaxIdle == that.poolMaxIdle &&
				poolMinIdle == that.poolMinIdle &&
				poolTestOnBorrow == that.poolTestOnBorrow &&
				poolTestOnReturn == that.poolTestOnReturn &&
				poolTestWhileIdle == that.poolTestWhileIdle &&
				numTestsPerEvictionRun == that.numTestsPerEvictionRun &&
				timeBetweenEvictionRunsMillis == that.timeBetweenEvictionRunsMillis &&
				check == that.check &&
				Objects.equals(nodes, that.nodes) &&
				Objects.equals(businessName, that.businessName) &&
				Objects.equals(systemName, that.systemName) &&
				Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nodes, businessName, systemName, connectionTimeout, soTimeout, maxWaitMillis, maxTotal, maxAttempts, poolMaxIdle, poolMinIdle, poolTestOnBorrow, poolTestOnReturn, poolTestWhileIdle, numTestsPerEvictionRun, timeBetweenEvictionRunsMillis, check, password);
	}

	@Override
	public String toString() {
		return "JedisClusterConfig{" +
				"nodes='" + nodes + '\'' +
				", businessName='" + businessName + '\'' +
				", systemName='" + systemName + '\'' +
				", connectionTimeout=" + connectionTimeout +
				", soTimeout=" + soTimeout +
				", maxWaitMillis=" + maxWaitMillis +
				", maxTotal=" + maxTotal +
				", maxAttempts=" + maxAttempts +
				", poolMaxIdle=" + poolMaxIdle +
				", poolMinIdle=" + poolMinIdle +
				", poolTestOnBorrow=" + poolTestOnBorrow +
				", poolTestOnReturn=" + poolTestOnReturn +
				", poolTestWhileIdle=" + poolTestWhileIdle +
				", numTestsPerEvictionRun=" + numTestsPerEvictionRun +
				", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
				", check=" + check +
				", password='" + password + '\'' +
				'}';
	}
}
