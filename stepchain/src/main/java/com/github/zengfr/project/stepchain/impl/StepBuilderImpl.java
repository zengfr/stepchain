package com.github.zengfr.project.stepchain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.Config;
import com.github.zengfr.project.stepchain.Step;
import com.github.zengfr.project.stepchain.StepBuilder;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class StepBuilderImpl implements StepBuilder {
	@Autowired
	Config config;

	@Override
	public <T> Step<T> createStep() {
		return createStep(-1);
	}

	@Override
	public <T> Step<T> createStep(int parallelCount) {
		return new StepImpl<>(parallelCount);
	}

	@Override
	public <T> Step<T> createStep(String parallelCountConfigName) {
		Integer parallelCount = config.get(parallelCountConfigName, -1);
		return createStep(parallelCount);
	}
}
