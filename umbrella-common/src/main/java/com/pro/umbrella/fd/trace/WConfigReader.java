package com.pro.umbrella.fd.trace;

import java.util.Map;

import com.google.common.collect.Maps;
import com.pro.umbrella.common.io.MapReader;
import com.pro.umbrella.common.io.Reader;

/**
 * {@link com.pro.umbrella.common.io.MapReader}
 *
 * @author Daniel Li
 * @since 18 August 2017
 */
public class WConfigReader implements Reader<String, Map<String, String>> {

	private MapReader<String> readOnlyReaer;

	private MapReader<String> writeReader;

	protected WConfigReader(Map<String, String> config) {
		this.writeReader = MapReader.from(Maps.newHashMap());
		this.readOnlyReaer = MapReader.from(config);
	}

	public static WConfigReader from(Map<String, String> map) {
		return new WConfigReader(map);
	}

	@Override
	public Map<String, String> getTarget() {
		return writeReader.getTarget();
	}

	@Override
	public String getString(String name) {
		return getString(name, null, false);
	}

	@Override
	public String getString(String name, String defValue) {
		return getString(name, defValue, false);
	}

	@Override
	public String getString(String name, String defValue, boolean checkContains) {
		String v;
		v = readOnlyReaer.getString(name);
		if (v == null && checkContains && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getString(name);
		if (v == null && checkContains && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Integer getInteger(String name) {
		return getInteger(name, null, false);
	}

	@Override
	public Integer getInteger(String name, Integer defValue) {
		return getInteger(name, defValue, false);
	}

	@Override
	public Integer getInteger(String name, Integer defValue, boolean checkContainsKey) {
		Integer v;
		v = readOnlyReaer.getInteger(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getInteger(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Long getLong(String name) {
		return getLong(name, null, false);
	}

	@Override
	public Long getLong(String name, Long defValue) {
		return getLong(name, defValue, false);
	}

	@Override
	public Long getLong(String name, Long defValue, boolean checkContainsKey) {
		Long v;
		v = readOnlyReaer.getLong(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getLong(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Float getFloat(String name) {
		return getFloat(name, null, false);
	}

	@Override
	public Float getFloat(String name, Float defValue) {
		return getFloat(name, defValue, false);
	}

	@Override
	public Float getFloat(String name, Float defValue, boolean checkContainsKey) {
		Float v;
		v = readOnlyReaer.getFloat(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getFloat(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Double getDouble(String name) {
		return getDouble(name, null, false);
	}

	@Override
	public Double getDouble(String name, Double defValue) {
		return getDouble(name, defValue, false);
	}

	@Override
	public Double getDouble(String name, Double defValue, boolean checkContainsKey) {
		Double v;
		v = readOnlyReaer.getDouble(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getDouble(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Boolean getBoolean(String name) {
		return getBoolean(name, null, false);
	}

	@Override
	public Boolean getBoolean(String name, Boolean defValue) {
		return getBoolean(name, defValue, false);
	}

	@Override
	public Boolean getBoolean(String name, Boolean defValue, boolean checkContainsKey) {
		Boolean v;
		v = readOnlyReaer.getBoolean(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getBoolean(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Byte getByte(String name) {
		return getByte(name, null, false);
	}

	@Override
	public Byte getByte(String name, Byte defValue) {
		return getByte(name, defValue, false);
	}

	@Override
	public Byte getByte(String name, Byte defValue, boolean checkContainsKey) {
		Byte v;
		v = readOnlyReaer.getByte(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getByte(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Short getShort(String name) {
		return getShort(name, null, false);
	}

	@Override
	public Short getShort(String name, Short defValue) {
		return getShort(name, defValue, false);
	}

	@Override
	public Short getShort(String name, Short defValue, boolean checkContainsKey) {
		Short v;
		v = readOnlyReaer.getShort(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getShort(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public Character getCharacter(String name) {
		return getCharacter(name, null, false);
	}

	@Override
	public Character getCharacter(String name, Character defValue) {
		return getCharacter(name, defValue, false);
	}

	@Override
	public Character getCharacter(String name, Character defValue, boolean checkContainsKey) {
		Character v;
		v = readOnlyReaer.getCharacter(name);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getCharacter(name);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public <E extends Enum<E>> E getEnum(String name, Class<E> clazz) {
		return getEnum(name, null, false, clazz);
	}

	@Override
	public <E extends Enum<E>> E getEnum(String name, E defValue, Class<E> clazz) {
		return getEnum(name, defValue, false, clazz);
	}

	@Override
	public <E extends Enum<E>> E getEnum(String name, E defValue, boolean checkContainsKey, Class<E> clazz) {
		E v;
		v = readOnlyReaer.getEnum(name, clazz);
		if (v == null && checkContainsKey && readOnlyReaer.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		v = writeReader.getEnum(name, clazz);
		if (v == null && checkContainsKey && writeReader.getTarget().containsKey(name)) {
			return null;
		}
		else if (v != null) {
			return v;
		}
		return defValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WConfigReader)) return false;

		WConfigReader that = (WConfigReader) o;

		if (readOnlyReaer != null ? !readOnlyReaer.equals(that.readOnlyReaer) : that.readOnlyReaer != null)
			return false;
		return writeReader != null ? writeReader.equals(that.writeReader) : that.writeReader == null;
	}

	@Override
	public int hashCode() {
		int result = readOnlyReaer != null ? readOnlyReaer.hashCode() : 0;
		result = 31 * result + (writeReader != null ? writeReader.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "{" + "readOnlyReaer=" + readOnlyReaer + ", writeReader=" + writeReader + '}';
	}
}
