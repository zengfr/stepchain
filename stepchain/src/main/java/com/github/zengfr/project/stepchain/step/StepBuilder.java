package com.github.zengfr.project.stepchain.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.IConfig;
import com.github.zengfr.project.stepchain.IStep;
import com.github.zengfr.project.stepchain.IStepBuilder;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class StepBuilder implements IStepBuilder {
	@Autowired
	protected IConfig config;

	@Override
	public <T> IStep<T> createStep() {
		return createStep(-1);
	}

	@Override
	public <T> IStep<T> createStep(int parallelCount) {
		return new Step<>(parallelCount);
	}

	@Override
	public <T> IStep<T> createStep(String parallelCountConfigName) {
		Integer parallelCount = config.get(parallelCountConfigName, -1);
		return createStep(parallelCount);
	}
}
