package com.pro.umbrella.common.jdbc.type;

import java.io.Serializable;

/**
 * 带有Code的枚举类型。
 *
 * @author Daniel Li
 * @since 13 August 2016
 */
public interface CodeEnum<E extends Enum<E>> extends Comparable<E>, Serializable {

	int getCode();
}
