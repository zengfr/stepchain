package com.github.zengfr.project.stepchain;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface StepBuilder {
	<T> Step<T> createStep();

	<T> Step<T> createStep(int parallelCount);

	<T> Step<T> createStep(String parallelCountConfigName);
}
