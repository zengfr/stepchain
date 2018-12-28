package com.github.zengfr.project.stepchain.processor;

import java.util.function.Function;

import com.github.zengfr.project.stepchain.AbstractProcessor;


/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class FunctionProcessor<I, O> extends AbstractProcessor<I, O> {
	protected Function<I, O> func;

	public FunctionProcessor(Function<I, O> func) {
		this.func = func;
	}

	@Override
	protected O execute(I context) throws Exception {
		return this.func.apply(context);
	}

}
