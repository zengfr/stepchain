package com.github.zengfr.project.stepchain.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.Config;
import com.google.common.collect.Maps;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class MemoryConfig implements Config {
	Map<String, Object> cfgs = Maps.newHashMap();

	@Override
	public String get(String key) {

		return (String) cfgs.get(key);
	}

	@Override
	public String get(String key, String defaultValue) {
		Object v = cfgs.get(key);
		return v != null ? "" + v : defaultValue;
	}

	@Override
	public void set(String key, String value) {
		cfgs.put(key, value);
	}

	@Override
	public Boolean get(String key, Boolean defaultValue) {
		String v = get(key, "" + defaultValue);
		return Boolean.valueOf(v);
	}

	@Override
	public Integer get(String key, Integer defaultValue) {
		String v = get(key, "" + defaultValue);
		return Integer.valueOf(v);
	}

	@Override
	public Long get(String key, Long defaultValue) {
		String v = get(key, "" + defaultValue);
		return Long.valueOf(v);
	}

}
