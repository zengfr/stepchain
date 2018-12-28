package com.github.zengfr.project.stepchain;

import java.util.function.Function;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface ChainBuilder {
	<A, B> Chain<A, B> createChain(Function<A, B> func);

	<A, B> Chain<A, B> createChain(Processor<A, B> processor);

	<A, B, C> Chain<A, C> createChain(Function<A, B> func1, Function<B, C> func2);

	<A, B, C> Chain<A, C> createChain(Processor<A, B> processor1, Processor<B, C> processor2);

	<A, B, C> Chain<A, C> createChain(Processor<A, B> processor, Chain<B, C> chain);

	<A, B, C> Chain<A, C> createChain(Chain<A, B> chain, Processor<B, C> processor);

	<A, B, C> Chain<A, C> createChain(Chain<A, B> chain1, Chain<B, C> chain2);
}