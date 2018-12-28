package com.github.zengfr.project.stepchain.chain;

import java.util.function.Function;

import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.util.ProcessorUtil;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
class ChainImpl<A, B> implements Chain<A, B> {
	protected Processor<A, B> processor;

	public ChainImpl(Processor<A, B> processor) {
		this.processor = processor;
	}

	@Override
	public <C> Chain<A, C> next(Processor<B, C> processor2) {
		Processor<A, C> process12 = ProcessorUtil.create(this.processor, processor2);
		return new ChainImpl<>(process12);
	}

	@Override
	public <C> Chain<A, C> next(Function<B, C> func) {
		Processor<B, C> process = ProcessorUtil.create(func);
		return next(process);
	}

	@Override
	public <C> Chain<A, C> next(Chain<B, C> chain) {
		return next((Processor<B, C>) chain);
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
