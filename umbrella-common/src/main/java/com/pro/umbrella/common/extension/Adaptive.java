package com.pro.umbrella.common.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pro.umbrella.common.extension.extractor.NameExtractor;
import com.pro.umbrella.common.extension.extractor.support.NullNameExtractor;

/**
 * 注解到扩展点方法的参数，表示这个参数用于提供信息，让自适应实例（Adaptive Instance）找到运行调用时要调用的扩展名称。
 *
 * @see ServiceLoader
 * @see SPI
 * @see Parameters
 * @see NameExtractor
 * @since 01 July 2016
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Adaptive {

	/**
	 * Key列表，这些Key在所注解参数的对应的Value是自适应实例方法调用时真正执行调用的扩展的名称。
	 * <br/>
	 * 如果这些Key在中所注解参数都没有Value，自适应实例会使用缺省扩展（在扩展点接口的{@link SPI}上设置的）。
	 * <p/>
	 * eg. <code>String[] {"key1", "key2"}</code>表示：
	 * <ol>
	 * <li>使用<code>key1</code>的值作为自适应实例真正调用的扩展实现的名称。
	 * <li>如果<code>key1</code>没有对应的值，则使用<code>key2</code>的值作为自适应实例真正调用的扩展实现的名称。
	 * <li>如果<code>key2</code>没有对应的值，则使用缺省扩展。
	 * <li>如果没有缺省扩展，则在获取自适应实例时，会抛出{@link IllegalStateException}。
	 * </ol>
	 * <p/>
	 * 缺省是扩展点接口名的点分小写形式。
	 * eg. 扩展点接口名<code>com.alibaba.cooma.FooBizService</code>, 缺省key是<code>String[] {"foo.biz.service"}</code>。
	 *
	 * @see SPI#value()
	 */
	String[] value() default {};

	/**
	 * Adaptive Instance执行扩展点调用时，通过{@link NameExtractor#extract(Class)}方法从扩展点方法参数上提取实际要调用的扩展名称的前缀。
	 */
	Class<? extends NameExtractor> extractor() default NullNameExtractor.class;

}