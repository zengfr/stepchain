package com.github.zengfr.project.stepchain.processor;

import com.github.zengfr.project.stepchain.AbstractProcessor;
import com.github.zengfr.project.stepchain.IProcessor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class CompositeProcessor<A, B, C> extends AbstractProcessor<A, C> {
	protected IProcessor<A, B> first;
	protected IProcessor<B, C> second;

	public CompositeProcessor(IProcessor<A, B> first, IProcessor<B, C> second) {
		this.first = first;
		this.second = second;
	}
	@Override
	protected C execute(A context) throws Exception {
		B b = first.process(context);
		return second.process(b);
	}

}
