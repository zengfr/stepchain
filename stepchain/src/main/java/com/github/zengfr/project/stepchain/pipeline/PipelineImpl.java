package com.github.zengfr.project.stepchain.pipeline;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.ChainBuilder;
import com.github.zengfr.project.stepchain.Pipeline;
import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.step.StepBuilderImpl;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class PipelineImpl extends StepBuilderImpl implements Pipeline {
	@Autowired
	protected ChainBuilder chainBuilder;

	@Override
	public <A, B> Chain<A, B> createChain(Function<A, B> func) {
		return chainBuilder.createChain(func);
	}

	@Override
	public <A, B> Chain<A, B> createChain(Processor<A, B> processor) {
		return chainBuilder.createChain(processor);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Function<A, B> func1, Function<B, C> func2) {
		return chainBuilder.createChain(func1, func2);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Processor<A, B> processor1, Processor<B, C> processor2) {
		return chainBuilder.createChain(processor1, processor2);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Processor<A, B> processor, Chain<B, C> chain) {
		return chainBuilder.createChain(processor, chain);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Chain<A, B> chain1, Chain<B, C> chain2) {
		return chainBuilder.createChain(chain1, chain2);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Chain<A, B> chain, Processor<B, C> processor) {
		return chainBuilder.createChain(chain, processor);
	}

}
