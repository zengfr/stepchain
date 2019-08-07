package com.github.zengfr.project.stepchain;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface IProcessorBuilder {
	<I, O> IProcessor<I, O> createScheduledFixedDelayProcessor(IProcessor<I, O> processor, Long milliseconds);

	<I, O> IProcessor<I, O> createScheduledFixedRateProcessor(IProcessor<I, O> processor, Long milliseconds);

	<I> IProcessor<I, Boolean> createProcessor(Predicate<I> predicate);

	<I> IProcessor<I, Boolean> createProcessor(Consumer<I> consumer);

	<I, O> IProcessor<I, O> createProcessor(Function<I, O> func);

	<A, B, C> IProcessor<A, C> createProcessor(IProcessor<A, B> first, IProcessor<B, C> second);

	<A, B, C, D> IProcessor<A, D> createProcessor(IProcessor<A, B> processor1, IProcessor<B, C> processor2, IProcessor<C, D> processor3);

	<I, O> IConditionLoopProcessor<I, O> createConditionLoopProcessor(IProcessor<I, Boolean> validator, IProcessor<I, O> processor);

	<I, O> IConditionValidatorProcessor<I, O> createConditionValidatorProcessor(IProcessor<I, Boolean> validator, IProcessor<I, O> trueBranch, IProcessor<I, O> falseBranch);

	<I, O> IConditionValidatorSelectorProcessor<I, O> createConditionValidatorSelectorProcessor();

	<I, S, O> IConditionSelectorProcessor<I, S, O> createConditionSelectorProcessor(IProcessor<I, S> selector);

	<I, S, O> IConditionSelectorProcessor<I, S, O> createConditionSelectorProcessor(IProcessor<I, S> selector, Map<S, IProcessor<I, O>> branchs);

}
