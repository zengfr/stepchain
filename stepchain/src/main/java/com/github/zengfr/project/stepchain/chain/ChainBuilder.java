package com.github.zengfr.project.stepchain.chain;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.IChain;
import com.github.zengfr.project.stepchain.IChainBuilder;
import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.util.ProcessorUtil;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class ChainBuilder implements IChainBuilder {
	@Override
	public <A, B> IChain<A, B> createChain(Function<A, B> func) {
		IProcessor<A, B> processor = ProcessorUtil.create(func);
		return createChain(processor);
	}

	@Override
	public <A, B> IChain<A, B> createChain(IProcessor<A, B> processor) {
		return new Chain<>(processor);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(Function<A, B> func1, Function<B, C> func2) {
		return createChain(func1).next(func2);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IProcessor<A, B> processor1, IProcessor<B, C> processor2) {
		return createChain(processor1).next(processor2);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IProcessor<A, B> processor1, IChain<B, C> chain) {
		return createChain(processor1).next(chain);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IChain<A, B> chain1, IChain<B, C> chain2) {
		return createChain(chain1).next(chain2);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IChain<A, B> chain, IProcessor<B, C> processor) {
		return chain.next(processor);
	}

}
