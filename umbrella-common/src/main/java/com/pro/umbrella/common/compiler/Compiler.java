package com.pro.umbrella.common.compiler;

import com.pro.umbrella.common.extension.SPI;

/**
 * Compiler. (SPI, Singleton, ThreadSafe)
 *
 * @author william.liangf
 */
@SPI("javassist")
public interface Compiler {

	/**
	 * Compile java source code.
	 *
	 * @param code        Java source code
	 * @param classLoader Class loader
	 * @return Compiled class
	 */
	Class<?> compile(String code, ClassLoader classLoader);

}
