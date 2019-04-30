package com.pro.umbrella.common.extension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pro.umbrella.common.compiler.Compiler;
import com.pro.umbrella.common.extension.extractor.NameExtractor;
import com.pro.umbrella.common.extension.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service使用的扩展点获取。<p>
 * <ul>
 * <li>自动注入关联扩展点。</li>
 * <li>自动Wrap上扩展点的Wrap类。</li>
 * <li>缺省获得的的扩展点是一个Adaptive Instance。
 * </ul>
 *
 * @see <a href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html#Service%20Provider">JDK5.0的自动发现机制实现</a>
 * @see SPI
 * @see Adaptive
 * @see Activate
 * @since 01 July 2016
 */
public class ServiceLoader<T> {

	private static final Logger logger = LoggerFactory.getLogger(ServiceLoader.class);

	private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

	private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_]+");

	private static final ConcurrentMap<Class<?>, ServiceLoader<?>> SERVICE_LOADERS = new ConcurrentHashMap<Class<?>, ServiceLoader<?>>();

	private static final Comparator<Object> ACTIVATE_COMPARATOR = (o1, o2) -> {
		if (o1 == null && o2 == null) {
			return 0;
		}
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		if (o1.equals(o2)) {
			return 0;
		}
		Activate a1 = o1.getClass().getAnnotation(Activate.class);
		Activate a2 = o2.getClass().getAnnotation(Activate.class);
		if ((a1.before().length > 0 || a1.after().length > 0 || a2.before().length > 0 || a2.after().length > 0)
				&& o1.getClass().getInterfaces().length > 0
				&& o1.getClass().getInterfaces()[0].isAnnotationPresent(SPI.class)) {
			ServiceLoader<?> serviceLoader = ServiceLoader.load(o1.getClass().getInterfaces()[0]);
			if (a1.before().length > 0 || a1.after().length > 0) {
				String n2 = serviceLoader.getName(o2.getClass());
				for (String before : a1.before()) {
					if (before.equals(n2)) {
						return -1;
					}
				}
				for (String after : a1.after()) {
					if (after.equals(n2)) {
						return 1;
					}
				}
			}
			if (a2.before().length > 0 || a2.after().length > 0) {
				String n1 = serviceLoader.getName(o1.getClass());
				for (String before : a2.before()) {
					if (before.equals(n1)) {
						return 1;
					}
				}
				for (String after : a2.after()) {
					if (after.equals(n1)) {
						return -1;
					}
				}
			}
		}
		int n1 = a1.order();
		int n2 = a2.order();
		return n1 > n2 ? 1 : -1; // 就算n1 == n2也不能返回0，否则在HashSet等集合中，会被认为是同一值而覆盖
	};

	private static final ConcurrentMap<Class<?>, Object> SERVICE_INSTANCES = new ConcurrentHashMap<>();

	private static final ConcurrentMap<Class<? extends NameExtractor>, NameExtractor> EXTRACTOR_INSTANCES = new ConcurrentHashMap<>();

	private static final String SERVICE_CONF_DIRECTORY = "META-INF/spi/";

	private static final String PREFIX_ADAPTIVE_CLASS = "*";

	private static final String PREFIX_WRAPPER_CLASS = "+";

	private final AtomicReference<Map<String, Class<?>>> nameMapping = new AtomicReference<>();

	private final ConcurrentMap<Class<?>, List<String>> serviceProviderClassMapping = new ConcurrentHashMap<>();

	private final Class<T> type;

	private final String defaultName;

	private final ServiceFactory objectFactory;

	private final ConcurrentMap<String, AtomicReference<T>> instances = new ConcurrentHashMap<>();

	private final ConcurrentMap<String, AtomicReference<Throwable>> createInstanceErrors = new ConcurrentHashMap<>();

	private final AtomicReference<T> adaptiveInstance = new AtomicReference<>();

	private final AtomicReference<Throwable> createAdaptiveInstanceError = new AtomicReference<>();

	private final Map<String, Activate> activatesNames = Collections.synchronizedMap(new LinkedHashMap<>());

	private volatile Class<? extends T> adaptiveClass = null;

	private Map<String, IllegalStateException> classLoadExceptions = new ConcurrentHashMap<>();

	private volatile Map<String, Map<String, String>> nameAttributesMapping;

	private volatile Set<Class<? extends T>> wrapperClasses;


	private ServiceLoader(Class<T> type) {
		this.type = type;

		String defaultName = null;
		final SPI annotation = type.getAnnotation(SPI.class);
		if (annotation != null) {
			String value = annotation.value();
			if ((value = value.trim()).length() > 0) {
				String[] names = NAME_SEPARATOR.split(value);
				if (names.length > 1) {
					throw new IllegalStateException("more than 1 default service name on service " + type.getName() +
							": " + Arrays.toString(names));
				}
				if (names.length == 1 && names[0].trim().length() > 0) {
					defaultName = names[0].trim();
				}
				if (!isValidServiceName(defaultName)) {
					throw new IllegalStateException("default name(" + defaultName + ") of service " + type.getName() +
							" is invalid!");
				}
			}
		}
		this.defaultName = defaultName;
		objectFactory = (type == ServiceFactory.class ? null : ServiceLoader.load(ServiceFactory.class).getAdaptive());
	}

	/**
	 * {@link ServiceLoader}的工厂方法。
	 *
	 * @param type 扩展点接口类型
	 * @param <T>  扩展点类型
	 * @return {@link ServiceLoader}实例
	 * @throws IllegalArgumentException 参数为<code>null</code>；
	 *                                  或是扩展点接口上没有{@link SPI}注解。
	 */
	@SuppressWarnings("unchecked")
	public static <T> ServiceLoader<T> load(Class<T> type) {
		if (type == null) {
			throw new IllegalArgumentException("Service type == null");
		}
		if (!type.isInterface()) {
			throw new IllegalArgumentException("Service type(" + type + ") is not interface!");
		}
		if (!withSPIAnnotation(type)) {
			throw new IllegalArgumentException("Service type(" + type + ") is not service, because WITHOUT @" +
					SPI.class.getSimpleName() + " Annotation!");
		}

		ServiceLoader<T> loader = (ServiceLoader<T>) SERVICE_LOADERS.get(type);
		if (loader == null) {
			SERVICE_LOADERS.putIfAbsent(type, new ServiceLoader<>(type));
			loader = (ServiceLoader<T>) SERVICE_LOADERS.get(type);
		}
		return loader;
	}

	private static <T> boolean withSPIAnnotation(Class<T> type) {
		return type.isAnnotationPresent(SPI.class);
	}

	private static boolean isValidServiceName(String name) {
		return NAME_PATTERN.matcher(name).matches();
	}

	private static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			return classLoader;
		}
		classLoader = ServiceLoader.class.getClassLoader();
		if (classLoader != null) {
			return classLoader;
		}
		return classLoader;
	}

	private static String toString(Throwable throwable) {
		StringWriter w = new StringWriter(1024);
		PrintWriter p = new PrintWriter(w);
		try {
			throwable.printStackTrace(p);
			return w.toString();
		}
		finally {
			p.close();
		}
	}

	/**
	 * <code>
	 * "attrib1=value1,attrib2=value2,isProvider,order=3" =>
	 * {"attrib1"="value1", "attrib2"="value2", "isProvider"="", "order"="3"}
	 * </code>
	 */
	private static Map<String, String> parseServiceAttribute(String attribute) {
		Map<String, String> ret = new HashMap<String, String>();
		if (attribute == null || attribute.length() == 0) {
			return ret;
		}

		String[] parts = attribute.split(",");
		for (String part : parts) {
			part = part.trim();
			int idx = part.indexOf('=');
			if (idx > 0) {
				ret.put(part.substring(0, idx).trim(),
						part.substring(idx + 1).trim());
			}
			else {
				ret.put(part, "");
			}
		}

		return ret;
	}

	private static boolean isEmpty(String value) {
		return value == null || value.length() == 0 || "false".equalsIgnoreCase(value) || "0".equalsIgnoreCase(value)
				|| "null".equalsIgnoreCase(value) || "N/A".equalsIgnoreCase(value);
	}

	/**
	 * 获取扩展点实现的所有扩展点名。
	 */
	public Set<String> getNames() {
		Map<String, Class<?>> clazzes = getServiceClasses();
		return Collections.unmodifiableSet(clazzes.keySet());
	}

	/**
	 * 返回缺省的扩展点名，如果没有设置缺省则返回<code>null</code>。
	 */
	public String getDefaultName() {
		return defaultName;
	}

	/**
	 * 返回缺省的扩展。
	 *
	 * @throws IllegalStateException 指定的扩展没有设置缺省扩展点
	 */
	public T getDefault() {
		if (null == defaultName || defaultName.length() == 0) {
			throw new IllegalStateException("No default service on service " + type.getName());
		}
		return get(defaultName);
	}

	public T getOrDefault(String name) {
		name = (name == null || name.length() == 0) ? defaultName : name;
		return get(name);
	}

	/**
	 * 检查是否有指定名字的扩展。
	 *
	 * @param name 扩展名
	 * @return 有指定名字的扩展，则<code>true</code>，否则<code>false</code>。
	 * @throws IllegalArgumentException 参数为<code>null</code>或是空字符串。
	 */
	public boolean has(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Service name == null");
		}
		return getServiceClasses().get(name) != null;
	}

	private Map<String, Class<?>> getServiceClasses() {
		Map<String, Class<?>> classes = nameMapping.get();
		if (classes == null) {
			synchronized (nameMapping) {
				classes = nameMapping.get();
				if (classes == null) { // double check
					loadServiceClasses0();
					classes = nameMapping.get();
				}
			}
		}
		return classes;
	}

	private Class<?> getServiceClass(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Service name == null");
		}
		Class<?> clazz = getServiceClasses().get(name);
		if (clazz == null) {
			throw findServiceClassLoadException(name);
		}
		return clazz;
	}

	private IllegalStateException findServiceClassLoadException(String name) {
		String msg = "No such service " + type.getName() + " by name " + name;

		for (Map.Entry<String, IllegalStateException> entry : classLoadExceptions.entrySet()) {
			if (entry.getKey().toLowerCase().contains(name.toLowerCase())) {
				IllegalStateException e = entry.getValue();
				return new IllegalStateException(msg + ", cause: " + e.getMessage(), e);
			}
		}

		StringBuilder buf = new StringBuilder(msg);
		if (!classLoadExceptions.isEmpty()) {
			buf.append(", possible causes: ");
			int i = 1;
			for (Map.Entry<String, IllegalStateException> entry : classLoadExceptions.entrySet()) {
				buf.append("\r\n(");
				buf.append(i++);
				buf.append(") ");
				buf.append(entry.getKey());
				buf.append(":\r\n");
				buf.append(toString(entry.getValue()));
			}
		}
		return new IllegalStateException(buf.toString());
	}

	@SuppressWarnings("unchecked")
	private T createService(String name) {
		Class<?> clazz = getServiceClass(name);
		try {
			T instance = (T) SERVICE_INSTANCES.get(clazz);
			if (instance == null) {
				Constructor<?> constructor = clazz.getDeclaredConstructor();
				constructor.setAccessible(true);
				SERVICE_INSTANCES.putIfAbsent(clazz, constructor.newInstance());
				instance = (T) SERVICE_INSTANCES.get(clazz);
			}
			initializingService(injectService(instance));
			Set<Class<? extends T>> wrapperClasses = this.wrapperClasses;
			if (wrapperClasses != null && wrapperClasses.size() > 0) {
				for (Class<? extends T> wrapperClass : wrapperClasses) {
					Constructor<? extends T> constructor = wrapperClass.getDeclaredConstructor(type);
					constructor.setAccessible(true);
					instance = initializingService(injectService(constructor.newInstance(instance)));
				}
			}
			return instance;
		}
		catch (Throwable t) {
			throw new IllegalStateException("Fail to create service " + name + " of service point " + type.getName() +
					", cause: " + t.getMessage(), t);
		}
	}

	private T injectService(T instance) {
		try {
			if (objectFactory != null) {
				for (Field field : instance.getClass().getDeclaredFields()) {
					Resource resource = field.getAnnotation(Resource.class);
					if (resource != null) {
						Class<?> type = resource.type();
						String name = resource.name();
						if (type == Object.class) {
							type = field.getType();
						}
						try {
							Object object = objectFactory.getService(type, name);
							if (object != null) {
								field.setAccessible(true);
								field.set(instance, object);
							}
						}
						catch (Exception e) {
							logger.error("Fail to inject via field " + field.getName() + " of interface to service " +
									"implementation " + instance.getClass() + " for service point " + type.getName() +
									", cause: " + e.getMessage(), e);
						}
					}
				}
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return instance;
	}

	private T initializingService(T instance) {
		try {
			if (objectFactory != null) {
				for (Method method : instance.getClass().getDeclaredMethods()) {
					if (method.getAnnotation(PostConstruct.class) != null) {
						try {
							method.setAccessible(true);
							method.invoke(instance);
						}
						catch (Exception e) {
							logger.error("Fail to inject postConstruct method " + method.getName() + " of interface to service " +
									"implementation " + instance.getClass() + " for service point " + type.getName() +
									", cause: " + e.getMessage(), e);
						}
					}
				}
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return instance;
	}

	/**
	 * 获取指定名字的扩展实例。
	 *
	 * @param name 扩展名
	 * @return 指定名字的扩展实例
	 * @throws IllegalArgumentException 参数为<code>null</code>或是空字符串。
	 * @throws IllegalStateException    指定的扩展名没有对应的扩展点，异常栈中包含可能的原因。
	 * @since 0.1.0
	 */
	public T get(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Service name == null");
		}
		// 先一下加载扩展点类，如果没有这个名字的扩展点类，会抛异常，
		// 这样不用创建不必要的Holder。
		getServiceClass(name);

		// 引入的Holder是为了下面用Holder作“细粒度锁”，而不是锁整个serviceInstances
		AtomicReference<T> holder = instances.get(name);
		if (holder == null) {
			instances.putIfAbsent(name, new AtomicReference<>());
			holder = instances.get(name);
		}
		AtomicReference<Throwable> throwableHolder = createInstanceErrors.get(name);
		if (throwableHolder == null) {
			createInstanceErrors.put(name, new AtomicReference<>());
			throwableHolder = createInstanceErrors.get(name);
		}

		if (throwableHolder.get() != null) {
			throw new IllegalStateException("Fail to get service " + name + " of service point " + type.getName() +
					", cause: " + throwableHolder.get().getMessage(), throwableHolder.get());
		}
		if (holder.get() == null) {
			synchronized (holder) {
				holder = instances.get(name);
				throwableHolder = createInstanceErrors.get(name);
				if (throwableHolder.get() != null) { // double check
					throw new IllegalStateException("Fail to get service " + name + " of service point " +
							type.getName() + ", cause: " + throwableHolder.get().getMessage(), throwableHolder.get());
				}
				if (holder.get() == null) {
					try {
						holder.set(createService(name));
					}
					catch (Throwable t) {
						throwableHolder.set(t);
						throw new IllegalStateException("Fail to get service " + name + " of service point " +
								type.getName() + ", cause: " + t.getMessage(), t);
					}
				}
			}
		}

		return holder.get();
	}

	private void loadServiceClasses0() {
		Map<String, Class<?>> serviceName2Class = new LinkedHashMap<>();
		Map<String, Map<String, String>> name2Attributes = new LinkedHashMap<>();
		String fileName = null;
		try {
			ClassLoader classLoader = getClassLoader();
			fileName = SERVICE_CONF_DIRECTORY + type.getName();
			Enumeration<URL> urls;
			if (classLoader != null) {
				urls = classLoader.getResources(fileName);
			}
			else {
				urls = ClassLoader.getSystemResources(fileName);
			}

			if (urls != null) { // 找到的urls为null，或是没有找到文件，即认为是没有找到扩展点
				while (urls.hasMoreElements()) {
					URL url = urls.nextElement();
					readService0(serviceName2Class, name2Attributes, classLoader, url);
				}
			}
		}
		catch (Throwable t) {
			logger.error("Exception when load service point(interface: " + type.getName() + ", description file: " +
					fileName + ").", t);
		}

		nameMapping.set(serviceName2Class);
		this.nameAttributesMapping = name2Attributes;
	}

	private void readService0(Map<String, Class<?>> serviceName2Class, Map<String, Map<String, String>> name2Attributes,
			ClassLoader classLoader, URL url) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				String config = line;

				// delete comments
				final int ci = config.indexOf('#');
				if (ci >= 0) config = config.substring(0, ci);
				config = config.trim();
				if (config.length() == 0) {
					continue;
				}
				String name = null;
				String body = null;
				String attribute = null;
				try {
					int i = config.indexOf('=');
					if (i > 0) {
						name = config.substring(0, i).trim();
						body = config.substring(i + 1).trim();
					}
					// 没有配置文件中没有扩展点名，从实现类的SPI注解上读取。
					if (name == null || name.length() == 0) {
						throw new IllegalStateException("missing service name, config value: " + config);
					}
					int j = config.indexOf("(", i);
					if (j > 0) {
						if (config.charAt(config.length() - 1) != ')') {
							throw new IllegalStateException("missing ')' of service attribute!");
						}
						body = config.substring(i + 1, j).trim();
						attribute = config.substring(j + 1, config.length() - 1);
					}

					Class<? extends T> clazz = Class.forName(body, true, classLoader).asSubclass(type);
					if (!type.isAssignableFrom(clazz)) {
						throw new IllegalStateException("Error when load service class(interface: " + type.getName() +
								", class line: " + clazz.getName() + "), class " + clazz.getName() +
								"is not subtype of interface.");
					}

					if (name.startsWith(PREFIX_ADAPTIVE_CLASS)) {
						if (adaptiveClass == null) {
							adaptiveClass = clazz;
						}
						else if (!adaptiveClass.equals(clazz)) {
							throw new IllegalStateException("More than 1 adaptive class found: " +
									adaptiveClass.getClass().getName() + ", " + clazz.getClass().getName());
						}
					}
					else {
						boolean isWrapper = name.startsWith(PREFIX_WRAPPER_CLASS);
						if (isWrapper) {
							try {
								clazz.getDeclaredConstructor(type);
								Set<Class<? extends T>> wrappers = wrapperClasses;
								if (wrappers == null) {
									wrapperClasses = new CopyOnWriteArraySet<>();
									wrappers = wrapperClasses;
								}
								wrappers.add(clazz);
							}
							catch (NoSuchMethodException e) {
								throw new IllegalStateException("wrapper class(" + clazz + ") has NO copy constructor!", e);
							}
						}
						else {
							String[] nameList = NAME_SEPARATOR.split(name);

							Activate activate = clazz.getAnnotation(Activate.class);
							if (activate != null) {
								activatesNames.put(nameList[0], activate);
							}

							for (String n : nameList) {
								if (!isValidServiceName(n)) {
									throw new IllegalStateException("name(" + n + ") of service " + type.getName() +
											"is invalid!");
								}

								try {
									clazz.getDeclaredConstructor();
								}
								catch (NoSuchMethodException e) {
									throw new IllegalStateException("service class(" + clazz + ") has NO default " +
											"constructor!", e);
								}
								if (serviceName2Class.containsKey(n)) {
									if (serviceName2Class.get(n) != clazz) {
										throw new IllegalStateException("Duplicate service " + type.getName() +
												" name " + n + " on " + clazz.getName() + " and " + clazz.getName());
									}
								}
								else {
									serviceName2Class.put(n, clazz);
								}
								name2Attributes.put(n, parseServiceAttribute(attribute));

								List<String> serviceNames = serviceProviderClassMapping.get(clazz);
								if (serviceNames == null) {
									serviceProviderClassMapping.put(clazz, serviceNames = new ArrayList<>(1));
								}
								serviceNames.add(n);
							}
						}
					}
				}
				catch (Throwable t) {
					IllegalStateException e = new IllegalStateException("Failed to load config line(" + line +
							") of config file(" + url + ") for service(" + type.getName() + "), cause: " +
							t.getMessage(), t);
					logger.warn("", e);
					if (name != null && name.startsWith(PREFIX_ADAPTIVE_CLASS)) {
						createAdaptiveInstanceError.set(t);
					}
					classLoadExceptions.put(line, e);
				}
			} // end of while read lines
		}
		catch (Throwable t) {
			logger.error("Exception when load service class(interface: " + type.getName() + ", class file: " + url +
					") in " + url, t);
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (Throwable t) {
					// ignore
				}
			}
		}
	}

	/**
	 * 取得Adaptive实例。
	 */
	public T getAdaptive() {
		getServiceClasses(); // 加载扩展点，保证会发现手写的AdaptiveClass

		Throwable createError = createAdaptiveInstanceError.get();
		T adaptiveInstance = this.adaptiveInstance.get();
		if (null != createError) {
			throw new IllegalStateException("Fail to create adaptive service for service point " + type.getName() +
					", cause: " + createError.getMessage(), createError);
		}
		if (null != adaptiveInstance) {
			return adaptiveInstance;
		}

		synchronized (this.adaptiveInstance) {
			createError = createAdaptiveInstanceError.get();
			adaptiveInstance = this.adaptiveInstance.get();
			if (null != createError) { // double check
				throw new IllegalStateException("Fail to create adaptive service for service point " + type.getName() +
						", cause: " + createError.getMessage(), createError);
			}
			if (null != adaptiveInstance) {
				return adaptiveInstance;
			}

			try {
				this.adaptiveInstance.set(createAdaptiveService());
				return this.adaptiveInstance.get();
			}
			catch (Throwable t) {
				createAdaptiveInstanceError.set(t);
				throw new IllegalStateException("Fail to create adaptive service for service point " + type.getName() +
						", cause: " + t.getMessage(), t);
			}
		}
	}

	private T createAdaptiveService() {
		try {
			Constructor<? extends T> constructor = getAdaptiveServiceClass().getDeclaredConstructor();
			constructor.setAccessible(true);
			return injectService(constructor.newInstance());
		}
		catch (Throwable e) {
			throw new IllegalStateException("Can not create adaptive service " + type + ", cause: " + e.getMessage(), e);
		}
	}

	private Class<? extends T> getAdaptiveServiceClass() {
		if (adaptiveClass != null) {
			return adaptiveClass;
		}
		return adaptiveClass = createAdaptiveServiceClass();
	}

	@SuppressWarnings("unchecked")
	private Class<? extends T> createAdaptiveServiceClass() {
		String code = createAdaptiveServiceClassCode();
		ClassLoader classLoader = getClassLoader();
		Compiler compiler = ServiceLoader.load(Compiler.class).getAdaptive();
		return (Class<? extends T>) compiler.compile(code, classLoader);
	}

	private String createAdaptiveServiceClassCode() {
		StringBuilder codeBuidler = new StringBuilder();
		Method[] methods = type.getMethods();
		boolean hasAdaptiveAnnotation = false;
		for (Method m : methods) {
			if (m.isAnnotationPresent(Adaptive.class)) {
				hasAdaptiveAnnotation = true;
				break;
			}
		}
		// 完全没有Adaptive方法，则不需要生成Adaptive类
		if (!hasAdaptiveAnnotation) {
			throw new IllegalStateException("No adaptive method on service " + type.getName() + ", refuse to create the " +
					"adaptive class!");
		}
		codeBuidler.append("package " + type.getPackage().getName() + ";");
		codeBuidler.append("\nimport " + ServiceLoader.class.getName() + ";");
		codeBuidler.append("\npublic class " + type.getSimpleName() + "$Adpative" + " implements " +
				type.getCanonicalName() + " {");

		for (Method method : methods) {
			Class<?> rt = method.getReturnType();
			Class<?>[] pts = method.getParameterTypes();
			Class<?>[] ets = method.getExceptionTypes();

			Adaptive adaptiveAnnotation = method.getAnnotation(Adaptive.class);
			StringBuilder code = new StringBuilder(512);
			if (adaptiveAnnotation == null) {
				code.append("throw new UnsupportedOperationException(\"method ").append(method.toString())
						.append(" of interface ").append(type.getName()).append(" is not adaptive method!\");");
			}
			else {
				int parameterTypeIndex = -1;
				for (int i = 0; i < pts.length; ++i) {
					if (Parameters.class.isAssignableFrom(pts[i])) {
						parameterTypeIndex = i;
						break;
					}
				}
				// 有类型为URL的参数
				if (parameterTypeIndex != -1) {
					// Null Point check
					String s = String.format("\nif (arg%d == null) throw new IllegalArgumentException(\"parameter == null\");",
							parameterTypeIndex);
					code.append(s);

					s = String.format("\n%s parameter = arg%d;", Parameters.class.getName(), parameterTypeIndex);
					code.append(s);
				}
				// 参数没有URL类型
				else {
					String attribMethod = null;

					// 找到参数的URL属性
					LBL_PTS:
					for (int i = 0; i < pts.length; ++i) {
						Method[] ms = pts[i].getMethods();
						for (Method m : ms) {
							String name = m.getName();
							if ((name.startsWith("get") || name.length() > 3) && Modifier.isPublic(m.getModifiers())
									&& !Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 0
									&& Parameters.class.isAssignableFrom(m.getReturnType())) {
								parameterTypeIndex = i;
								attribMethod = name;
								break LBL_PTS;
							}
						}
					}
					if (attribMethod == null) {
						throw new IllegalStateException("fail to create adative class for interface " + type.getName()
								+ ": not found parameter or parameter attribute in parameters of method " +
								method.getName());
					}

					// Null point check
					String s = String.format("\nif (arg%d == null) throw new IllegalArgumentException(\"%s argument == null\");",
							parameterTypeIndex, pts[parameterTypeIndex].getName());
					code.append(s);
					s = String.format("\nif (arg%d.%s() == null) throw new IllegalArgumentException(\"%s argument %s() == null\");",
							parameterTypeIndex, attribMethod, pts[parameterTypeIndex].getName(), attribMethod);
					code.append(s);

					s = String.format("%s parameter = arg%d.%s();", Parameters.class.getName(), parameterTypeIndex, attribMethod);
					code.append(s);
				}

				String[] value = adaptiveAnnotation.value();
				// 没有设置Key，则使用“扩展点接口名的点分隔 作为Key
				if (value.length == 0) {
					char[] charArray = type.getSimpleName().toCharArray();
					StringBuilder sb = new StringBuilder(128);
					for (int i = 0; i < charArray.length; i++) {
						if (Character.isUpperCase(charArray[i])) {
							if (i != 0) {
								sb.append(".");
							}
							sb.append(Character.toLowerCase(charArray[i]));
						}
						else {
							sb.append(charArray[i]);
						}
					}
					value = new String[] {sb.toString()};
				}

				code.append("\nString extractorName = null;");
				Class<? extends NameExtractor> extractorClazz = adaptiveAnnotation.extractor();
				if (extractorClazz != null) {
					NameExtractor nameExtractor;
					try {
						nameExtractor = EXTRACTOR_INSTANCES.get(extractorClazz);
						if (nameExtractor == null) {
							Constructor<? extends NameExtractor> constructor = extractorClazz.getDeclaredConstructor();
							constructor.setAccessible(true);
							EXTRACTOR_INSTANCES.putIfAbsent(extractorClazz, constructor.newInstance());
							nameExtractor = EXTRACTOR_INSTANCES.get(extractorClazz);
						}
					}
					catch (Exception e) {
						throw new IllegalStateException("fail to create adative class for interface " + type.getName()
								+ ": fail to create extractor instance " + extractorClazz.getName());
					}
					for (int i = 0; i < pts.length; ++i) {
						String methodName = nameExtractor.extract(pts[i]);
						if (methodName != null) {
							String s = String.format("\nif (extractorName == null && arg%d != null) extractorName = arg%d.%s();", i, i, methodName);
							code.append(s);
						}
					}

				}
				String defaultServiceName = defaultName;
				String getNameCode = null;
				for (int i = value.length - 1; i >= 0; --i) {
					if (i == value.length - 1) {
						if (null != defaultServiceName) {
							getNameCode = String.format("parameter.getParameter(extractorName == null ? \"%s\" : " +
									"extractorName + \".%s\", \"%s\")", value[i], value[i], defaultServiceName);
						}
						else {
							getNameCode = String.format("parameter.getParameter(extractorName == null ? \"%s\" : " +
									"extractorName + \".%s\")", value[i], value[i]);
						}
					}
					else {
						getNameCode = String.format("parameter.getParameter(extractorName == null ? \"%s\" : " +
								"extractorName + \".%s\", %s)", value[i], value[i], getNameCode);
					}
				}
				code.append("\nString serviceName = ").append(getNameCode).append(";");
				// check serviceName == null?
				String s = String.format("\nif(serviceName == null) throw new IllegalStateException(\"Fail to get service(%s) name " +
						"from parameter(\" + parameter.toString() + \") use keys(%s)\");", type.getName(), Arrays.toString(value));
				code.append(s);

				s = String.format("\n%s service = (%<s)%s.load(%s.class).get(serviceName);", type.getName(),
						ServiceLoader.class.getSimpleName(), type.getName());
				code.append(s);

				// return statement
				if (!rt.equals(void.class)) {
					code.append("\nreturn ");
				}

				s = String.format("service.%s(", method.getName());
				code.append(s);
				for (int i = 0; i < pts.length; i++) {
					if (i != 0)
						code.append(", ");
					code.append("arg").append(i);
				}
				code.append(");");
			}

			codeBuidler.append("\npublic " + rt.getCanonicalName() + " " + method.getName() + "(");
			for (int i = 0; i < pts.length; i++) {
				if (i > 0) {
					codeBuidler.append(", ");
				}
				codeBuidler.append(pts[i].getCanonicalName());
				codeBuidler.append(" ");
				codeBuidler.append("arg" + i);
			}
			codeBuidler.append(")");
			if (ets.length > 0) {
				codeBuidler.append(" throws ");
				for (int i = 0; i < ets.length; i++) {
					if (i > 0) {
						codeBuidler.append(", ");
					}
					codeBuidler.append(ets[i].getCanonicalName());
				}
			}
			codeBuidler.append(" {");
			codeBuidler.append(code.toString());
			codeBuidler.append("\n}");
		}
		codeBuidler.append("\n}");
		if (logger.isDebugEnabled()) {
			logger.debug(codeBuidler.toString());
		}
		return codeBuidler.toString();
	}

	public Map<String, Map<String, String>> getAttributes() {
		// 先一下加载扩展点类
		getServiceClasses();

		return nameAttributesMapping;
	}

	public Map<String, String> getAttribute(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("Service name == null");

		// 先一下加载扩展点类，如果没有这个名字的扩展点类，会抛异常，
		// 这样不用创建不必要的Holder。
		getServiceClass(name);

		return nameAttributesMapping.get(name);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "<" + type.getName() + ">";
	}

	public List<T> getActivate(Parameters parameters, String key) {
		return getActivate(parameters, key, null);
	}

	public List<T> getActivate(Parameters parameters, Collection<String> values) {
		return getActivate(parameters, values, null);
	}

	public List<T> getActivate(Parameters parameters, String key, String group) {
		String value = parameters.getParameter(key);
		List<String> values = value == null || value.length() == 0 ? null : Arrays.asList(NAME_SEPARATOR.split(value));
		return getActivate(parameters, values, group);
	}

	public List<T> getActivate(Parameters parameters, Collection<String> values, String group) {
		List<T> services = new ArrayList<>();
		if (values == null) {
			values = Collections.EMPTY_LIST;
		}

		getServiceClasses();

		if (values.isEmpty()) {
			for (Map.Entry<String, Activate> entry : activatesNames.entrySet()) {
				String name = entry.getKey();
				Activate activate = entry.getValue();
				addActivateService(group, activate, name, parameters, services);
			}
			Collections.sort(services, ACTIVATE_COMPARATOR);
		}
		else {
			for (String name : values) {
				Activate activate = activatesNames.get(name);
				if (activate == null) {
					continue;
				}
				addActivateService(group, activate, name, parameters, services);
			}
		}
		return services;
	}

	private void addActivateService(String group, Activate activate, String name, Parameters parameters, List<T>
			services) {
		if (isMatchGroup(group, activate.group())) {
			T service = get(name);
			if (isActive(activate, parameters)) {
				services.add(service);
			}
		}
	}

	private boolean isMatchGroup(String group, String[] groups) {
		if (group == null || group.length() == 0) {
			return true;
		}
		if (groups != null && groups.length > 0) {
			for (String g : groups) {
				if (group.equals(g)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isActive(Activate activate, Parameters parameters) {
		String[] keys = activate.value();
		if (keys.length == 0) {
			return true;
		}
		for (String key : keys) {
			for (Map.Entry<String, String> entry : parameters.getParameters().entrySet()) {
				String k = entry.getKey();
				String v = entry.getValue();
				if ((k.equals(key) || k.endsWith("." + key)) && !isEmpty(v)) {
					return true;
				}
			}
		}
		return false;
	}

	public String getName(T serviceProvider) {
		return getName(serviceProvider.getClass());
	}

	public String getName(Class<?> serviceProviderClass) {
		List<String> target = serviceProviderClassMapping.get(serviceProviderClass);
		if (target == null) {
			return null;
		}
		if (target.size() > 1) {
			throw new IllegalArgumentException("Service provider class: " + serviceProviderClass + " size great than 1");
		}
		return target.get(0);
	}

	public List<String> getNames(T serviceProvider) {
		return getNames(serviceProvider.getClass());
	}

	public List<String> getNames(Class<?> serviceProviderClass) {
		return serviceProviderClassMapping.get(serviceProviderClass);
	}

}
