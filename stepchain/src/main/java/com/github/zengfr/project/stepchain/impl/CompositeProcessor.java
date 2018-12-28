package com.github.zengfr.project.stepchain.impl;

import com.github.zengfr.project.stepchain.Processor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class CompositeProcessor<A, B, C> implements Processor<A, C> {
	public CompositeProcessor(Processor<A, B> first, Processor<B, C> second) {
		super();
		this.first = first;
		this.second = second;
	}

	protected Processor<A, B> first;
	protected Processor<B, C> second;

	@Override
	public Boolean isEnabled() {
		return true;
	}

	@Override
	public C process(A context) throws Exception {
		B b = first.process(context);
		return second.process(b);
	}

}
