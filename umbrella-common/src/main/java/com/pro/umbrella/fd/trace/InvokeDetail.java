package com.pro.umbrella.fd.trace;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by liangxiao on 2018/3/29.
 */
public class InvokeDetail implements Serializable {
	private static final long serialVersionUID = -8563398580436570488L;

	private String appName;

	private Long taskId; // 一次收集任务对应的id

	private String className;

	private String type;

	private int lineNum;

	private String methodName;

	private Class<?>[] parameterTypes;

	private Object[] arguments;

	private Class<?> resultType;

	private Object returnResult;

	private Class<?> throwType;

	private Throwable throwResult;

	private Endpoint endpoint;

	private String invokeSide;

	// 是否是入口方法
	private boolean isEntrance = false;

	// 有些方法是依赖于成员变量做逻辑的，成员变量的瞬间值可放在这里
	private Map<String, Object> attachment;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(Object returnResult) {
		this.returnResult = returnResult;
	}

	public Throwable getThrowResult() {
		return throwResult;
	}

	public void setThrowResult(Throwable throwResult) {
		this.throwResult = throwResult;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	public String getInvokeSide() {
		return invokeSide;
	}

	public void setInvokeSide(String invokeSide) {
		this.invokeSide = invokeSide;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public boolean getIsEntrance() {
		return isEntrance;
	}

	public void setIsEntrance(boolean entrance) {
		isEntrance = entrance;
	}

	public Map<String, Object> getAttachment() {
		return attachment;
	}

	public void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}

	public Class<?> getResultType() {
		return resultType;
	}

	public void setResultType(Class<?> resultType) {
		this.resultType = resultType;
	}

	public Class<?> getThrowType() {
		return throwType;
	}

	public void setThrowType(Class<?> throwType) {
		this.throwType = throwType;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;

		InvokeDetail that = (InvokeDetail) object;

		if (lineNum != that.lineNum) return false;
		if (isEntrance != that.isEntrance) return false;
		if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
		if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
		if (className != null ? !className.equals(that.className) : that.className != null) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;
		if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(parameterTypes, that.parameterTypes)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(arguments, that.arguments)) return false;
		if (resultType != null ? !resultType.equals(that.resultType) : that.resultType != null) return false;
		if (returnResult != null ? !returnResult.equals(that.returnResult) : that.returnResult != null) return false;
		if (throwType != null ? !throwType.equals(that.throwType) : that.throwType != null) return false;
		if (throwResult != null ? !throwResult.equals(that.throwResult) : that.throwResult != null) return false;
		if (endpoint != null ? !endpoint.equals(that.endpoint) : that.endpoint != null) return false;
		if (invokeSide != null ? !invokeSide.equals(that.invokeSide) : that.invokeSide != null) return false;
		return attachment != null ? attachment.equals(that.attachment) : that.attachment == null;
	}

	@Override
	public int hashCode() {
		int result = appName != null ? appName.hashCode() : 0;
		result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
		result = 31 * result + (className != null ? className.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + lineNum;
		result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(parameterTypes);
		result = 31 * result + Arrays.hashCode(arguments);
		result = 31 * result + (resultType != null ? resultType.hashCode() : 0);
		result = 31 * result + (returnResult != null ? returnResult.hashCode() : 0);
		result = 31 * result + (throwType != null ? throwType.hashCode() : 0);
		result = 31 * result + (throwResult != null ? throwResult.hashCode() : 0);
		result = 31 * result + (endpoint != null ? endpoint.hashCode() : 0);
		result = 31 * result + (invokeSide != null ? invokeSide.hashCode() : 0);
		result = 31 * result + (isEntrance ? 1 : 0);
		result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "InvokeDetail{" +
				"appName='" + appName + '\'' +
				", taskId=" + taskId +
				", className='" + className + '\'' +
				", type='" + type + '\'' +
				", lineNum=" + lineNum +
				", methodName='" + methodName + '\'' +
				", parameterTypes=" + Arrays.toString(parameterTypes) +
				", arguments=" + Arrays.toString(arguments) +
				", resultType=" + resultType +
				", returnResult=" + returnResult +
				", throwType=" + throwType +
				", throwResult=" + throwResult +
				", endpoint=" + endpoint +
				", invokeSide='" + invokeSide + '\'' +
				", isEntrance=" + isEntrance +
				", attachment=" + attachment +
				'}';
	}
}
