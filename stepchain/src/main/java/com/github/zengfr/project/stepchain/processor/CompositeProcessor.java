package com.github.zengfr.project.stepchain.processor;

import com.github.zengfr.project.stepchain.AbstractProcessor;
import com.github.zengfr.project.stepchain.Processor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class CompositeProcessor<A, B, C> extends AbstractProcessor<A, C> {
	protected Processor<A, B> first;
	protected Processor<B, C> second;

	public CompositeProcessor(Processor<A, B> first, Processor<B, C> second) {
		this.first = first;
		this.second = second;
	}
	@Override
	protected C execute(A context) throws Exception {
		B b = first.process(context);
		return second.process(b);
	}

}
