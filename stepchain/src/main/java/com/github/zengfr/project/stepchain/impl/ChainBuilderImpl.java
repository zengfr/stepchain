package com.github.zengfr.project.stepchain.impl;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.ChainBuilder;
import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.util.ProcessorUtil;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class ChainBuilderImpl implements ChainBuilder {
	@Override
	public <A, B> Chain<A, B> createChain(Function<A, B> func) {
		Processor<A, B> processor = ProcessorUtil.create(func);
		return createChain(processor);
	}

	@Override
	public <A, B> Chain<A, B> createChain(Processor<A, B> processor) {
		return new ChainImpl<>(processor);
	}

	@Override
	public <A, B, C> Chain<A, C> createChain(Processor<A, B> processor1, Processor<B, C> processor2) {
		return createChain(processor1).next(processor2);
	}

}
