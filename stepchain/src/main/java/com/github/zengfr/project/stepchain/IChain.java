package com.github.zengfr.project.stepchain;

import java.util.function.Function;

/**
 * zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 * https://github.com/zengfr/stepchain-spring-boot-starter
 * 
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface IChain<A, B> extends IProcessor<A, B> {
	<C> IChain<A, C> next(IProcessor<B, C> process);

	<C> IChain<A, C> next(Function<B, C> func);

	<C> IChain<A, C> next(IChain<B, C> chain);
}
