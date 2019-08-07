package com.github.zengfr.project.stepchain;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 * https://github.com/zengfr/stepchain-spring-boot-starter
 * 
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface IStep<I> extends IStepProcessor<I>, IProcessor<I, Boolean> {
	void clear();

	void put(Iterable<IProcessor<I, Boolean>> processors);

	void put(Collection<IProcessor<I, Boolean>> processors);

	void put(List<IProcessor<I, Boolean>> processors);

	void put(IProcessor<I, Boolean> processor);

	void put(IProcessor<I, Boolean>... processorArray);

	void put(IChain<I, Boolean> chain);

	void put(IChain<I, Boolean>... chainArray);

	void put(Function<I, Boolean> func);

	void put(Function<I, Boolean>... funcArray);

	void put(Predicate<I> predicate);

	void put(Predicate<I>... predicateArray);

	void put(Consumer<I> consumer);

	void put(Consumer<I>... consumerArray);

	void put(IStepProcessor<I> processor);

	void put(IStepProcessor<I>... processorArray);

	void put(IStep<I> step);

	void put(IStep<I>... stepArray);
}
