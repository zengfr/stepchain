package com.github.zengfr.project.stepchain;

import java.util.function.Function;

/**
 * zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 * https://github.com/zengfr/stepchain-spring-boot-starter
 * 
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface Chain<A, B> extends Processor<A, B> {
	<C> Chain<A, C> next(Processor<B, C> process);

	<C> Chain<A, C> next(Function<B, C> func);

	<C> Chain<A, C> next(Chain<B, C> chain);
}
