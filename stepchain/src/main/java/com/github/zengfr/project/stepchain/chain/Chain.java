package com.github.zengfr.project.stepchain.chain;

import java.util.function.Function;

import com.github.zengfr.project.stepchain.IChain;
import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.util.ProcessorUtil;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
class Chain<A, B> implements IChain<A, B> {
	protected IProcessor<A, B> processor;

	public Chain(IProcessor<A, B> processor) {
		this.processor = processor;
	}

	@Override
	public <C> IChain<A, C> next(IProcessor<B, C> processor2) {
		IProcessor<A, C> process12 = ProcessorUtil.create(this.processor, processor2);
		return new Chain<>(process12);
	}

	@Override
	public <C> IChain<A, C> next(Function<B, C> func) {
		IProcessor<B, C> process = ProcessorUtil.create(func);
		return next(process);
	}

	@Override
	public <C> IChain<A, C> next(IChain<B, C> chain) {
		return next((IProcessor<B, C>) chain);
	}

	@Override
	public Boolean isEnabled() {
		return true;
	}

	@Override
	public B process(A context) throws Exception {
		return processor.process(context);
	}

}
