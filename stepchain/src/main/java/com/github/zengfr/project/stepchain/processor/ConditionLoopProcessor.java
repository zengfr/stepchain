package com.github.zengfr.project.stepchain.processor;

import com.github.zengfr.project.stepchain.AbstractProcessor;
import com.github.zengfr.project.stepchain.IConditionLoopProcessor;
import com.github.zengfr.project.stepchain.IProcessor;
/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class ConditionLoopProcessor<I, O> extends AbstractProcessor<I, O> implements IConditionLoopProcessor<I, O> {
	private IProcessor<I,Boolean> validator;
	private IProcessor<I, O> processor;

	@Override
	public void setProcessor(IProcessor<I, O> processor) {
		this.processor = processor;
	}

	@Override
	public void setValidator(IProcessor<I,Boolean> validator) {
		this.validator = validator;
	}

	@Override
	protected O execute(I context) throws Exception {
		O o;
		do {
			o = this.processor.process(context);
		} while (validator.process(context));
		return o;
	}

}
