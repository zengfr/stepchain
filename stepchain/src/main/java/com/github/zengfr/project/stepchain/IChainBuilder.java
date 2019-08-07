package com.github.zengfr.project.stepchain;

import java.util.function.Function;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface IChainBuilder {
	<A, B> IChain<A, B> createChain(Function<A, B> func);

	<A, B> IChain<A, B> createChain(IProcessor<A, B> processor);

	<A, B, C> IChain<A, C> createChain(Function<A, B> func1, Function<B, C> func2);

	<A, B, C> IChain<A, C> createChain(IProcessor<A, B> processor1, IProcessor<B, C> processor2);

	<A, B, C> IChain<A, C> createChain(IProcessor<A, B> processor, IChain<B, C> chain);

	<A, B, C> IChain<A, C> createChain(IChain<A, B> chain, IProcessor<B, C> processor);

	<A, B, C> IChain<A, C> createChain(IChain<A, B> chain1, IChain<B, C> chain2);
}