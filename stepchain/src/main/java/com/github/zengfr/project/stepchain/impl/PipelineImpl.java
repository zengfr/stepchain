package com.github.zengfr.project.stepchain.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.ChainBuilder;
import com.github.zengfr.project.stepchain.Pipeline;
import com.github.zengfr.project.stepchain.Processor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class PipelineImpl extends StepBuilderImpl implements Pipeline {
	@Autowired
	ChainBuilder chainBuilder;

	@Override
	public <A, B> Chain<A, B> createChain(Function<A, B> func) {
		return chainBuilder.createChain(func);
	}

	@Override
	public <A, B> Chain<A, B> createChain(Processor<A, B> processor) {
		return chainBuilder.createChain(processor);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Processor<A, B> processor1, Processor<B, C> processor2) {
		return chainBuilder.createChain(processor1, processor2);
	}

}
