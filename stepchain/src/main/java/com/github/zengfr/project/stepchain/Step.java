package com.github.zengfr.project.stepchain;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 * https://github.com/zengfr/stepchain-spring-boot-starter
 */
import java.util.Collection;
import java.util.function.Function;

public interface Step<I> extends StepProcessor<I> {
	void put(StepProcessor<I> processor);

	void put(StepProcessor<I>... processorArray);

	void put(Collection<StepProcessor<I>> processors);

	void put(Processor<I, Boolean> processor);

	void put(Processor<I, Boolean>... processorArray);

	void put(Chain<I, Boolean> chain);

	void put(Chain<I, Boolean>... processorArray);

	void put(Function<I, Boolean> func);

	void put(Function<I, Boolean>... processorArray);
}
