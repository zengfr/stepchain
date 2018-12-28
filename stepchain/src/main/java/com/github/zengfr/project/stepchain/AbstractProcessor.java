package com.github.zengfr.project.stepchain;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public abstract class AbstractProcessor<I, O> implements Processor<I, O> {
	@Autowired
	Config config;

	public Boolean isEnabled() {
		if (config != null)
			return config.get(this.getClass().getName(), true);
		return true;
	}

	public O process(I context) throws Exception {
		if (isEnabled()) {
			return execute(context);
		}
		return null;
	}

	protected abstract O execute(I context) throws Exception;
}
