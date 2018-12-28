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
public interface Step<I> extends StepProcessor<I>, Processor<I, Boolean> {
	void clear();

	void put(Iterable<Processor<I, Boolean>> processors);

	void put(Collection<Processor<I, Boolean>> processors);

	void put(List<Processor<I, Boolean>> processors);

	void put(Processor<I, Boolean> processor);

	void put(Processor<I, Boolean>... processorArray);

	void put(Chain<I, Boolean> chain);

	void put(Chain<I, Boolean>... chainArray);

	void put(Function<I, Boolean> func);

	void put(Function<I, Boolean>... funcArray);

	void put(Predicate<I> predicate);

	void put(Predicate<I>... predicateArray);

	void put(Consumer<I> consumer);

	void put(Consumer<I>... consumerArray);

	void put(StepProcessor<I> processor);

	void put(StepProcessor<I>... processorArray);

	void put(Step<I> step);

	void put(Step<I>... stepArray);
}
