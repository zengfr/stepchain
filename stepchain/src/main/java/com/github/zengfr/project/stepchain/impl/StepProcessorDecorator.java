package com.github.zengfr.project.stepchain.impl;

import com.github.zengfr.project.stepchain.AbstractStepProcessor;
import com.github.zengfr.project.stepchain.Processor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class StepProcessorDecorator<A> extends AbstractStepProcessor<A> {
	protected Processor<A, Boolean> processor;

	public StepProcessorDecorator(Processor<A, Boolean> processor) {
		this.processor = processor;
	}

	@Override
	protected Boolean execute(A context) throws Exception {

		return processor.process(context);
	}

}
