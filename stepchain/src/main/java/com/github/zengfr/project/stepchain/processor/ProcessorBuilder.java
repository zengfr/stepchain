package com.github.zengfr.project.stepchain.processor;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.IConditionLoopProcessor;
import com.github.zengfr.project.stepchain.IConditionSelectorProcessor;
import com.github.zengfr.project.stepchain.IConditionValidatorProcessor;
import com.github.zengfr.project.stepchain.IConditionValidatorSelectorProcessor;
import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.IProcessorBuilder;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class ProcessorBuilder implements IProcessorBuilder {
	@Override
	public <I> IProcessor<I, Boolean> createProcessor(Predicate<I> predicate) {
		return new PredicateProcessor<>(predicate);
	}

	@Override
	public <I> IProcessor<I, Boolean> createProcessor(Consumer<I> consumer) {
		return new ConsumerProcessor<>(consumer);
	}

	@Override
	public <I, O> IProcessor<I, O> createProcessor(Function<I, O> func) {
		return new FunctionProcessor<>(func);
	}

	@Override
	public <A, B, C> IProcessor<A, C> createProcessor(IProcessor<A, B> first, IProcessor<B, C> second) {

		return new CompositeProcessor<>(first, second);
	}

	@Override
	public <A, B, C, D> IProcessor<A, D> createProcessor(IProcessor<A, B> processor1, IProcessor<B, C> processor2, IProcessor<C, D> processor3) {

		return new CompositeProcessor<>(createProcessor(processor1, processor2), processor3);
	}

	@Override
	public <I, O> IConditionLoopProcessor<I, O> createConditionLoopProcessor(IProcessor<I, Boolean> validator, IProcessor<I, O> processor) {
		IConditionLoopProcessor<I, O> loopProcessor = new ConditionLoopProcessor<>();
		loopProcessor.setValidator(validator);
		loopProcessor.setProcessor(processor);
		return loopProcessor;
	}

	@Override
	public <I, O> IConditionValidatorProcessor<I, O> createConditionValidatorProcessor(IProcessor<I, Boolean> validator, IProcessor<I, O> trueBranch, IProcessor<I, O> falseBranch) {

		IConditionValidatorProcessor<I, O> selectorProcessor = new ConditionValidatorProcessor<>();
		selectorProcessor.setSelector(validator);
		selectorProcessor.setBranch(true, trueBranch);
		selectorProcessor.setBranch(false, falseBranch);
		return selectorProcessor;
	}

	@Override
	public <I, O> IConditionValidatorSelectorProcessor<I, O> createConditionValidatorSelectorProcessor() {
		IConditionValidatorSelectorProcessor<I, O> processor = new ConditionValidatorSelectorProcessor<I, O>();
		return processor;
	}

	@Override
	public <I, S, O> IConditionSelectorProcessor<I, S, O> createConditionSelectorProcessor(IProcessor<I, S> selector) {
		IConditionSelectorProcessor<I, S, O> selectorProcessor = new ConditionSelectorProcessor<>();
		selectorProcessor.setSelector(selector);
		return selectorProcessor;
	}

	@Override
	public <I, S, O> IConditionSelectorProcessor<I, S, O> createConditionSelectorProcessor(IProcessor<I, S> selector, Map<S, IProcessor<I, O>> branchs) {

		IConditionSelectorProcessor<I, S, O> selectorProcessor = new ConditionSelectorProcessor<>();
		selectorProcessor.setSelector(selector);

		for (Map.Entry<S, IProcessor<I, O>> branch : branchs.entrySet()) {
			selectorProcessor.setBranch(branch.getKey(), branch.getValue());
		}
		return selectorProcessor;
	}

	@Override
	public <I, O> IProcessor<I, O> createScheduledFixedDelayProcessor(IProcessor<I, O> processor, Long milliseconds) {
		IProcessor<I, O> scheduledProcessor = new ScheduledFixedDelayProcessor<>(processor, milliseconds);

		return scheduledProcessor;
	}

	@Override
	public <I, O> IProcessor<I, O> createScheduledFixedRateProcessor(IProcessor<I, O> processor, Long milliseconds) {
		IProcessor<I, O> scheduledProcessor = new ScheduledFixedRateProcessor<>(processor, milliseconds);

		return scheduledProcessor;
	}
}
