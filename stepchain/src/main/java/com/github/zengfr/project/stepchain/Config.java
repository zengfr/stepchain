package com.github.zengfr.project.stepchain;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface Config {
	String get(String key);

	void set(String key, String value);

	String get(String key, String defaultValue);

	Boolean get(String key, Boolean defaultValue);

	Integer get(String key, Integer defaultValue);

	Long get(String key, Long defaultValue);

}
