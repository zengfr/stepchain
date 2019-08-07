package com.github.zengfr.project.stepchain.pipeline;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.*;
import com.github.zengfr.project.stepchain.context.BinaryContext;
import com.github.zengfr.project.stepchain.context.DataContext;
import com.github.zengfr.project.stepchain.context.TernaryContext;
import com.github.zengfr.project.stepchain.context.UnaryContext;
import com.github.zengfr.project.stepchain.step.StepBuilder;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Component
public class Pipeline implements IPipeline {
	@Autowired
	protected IContextBuilder contextBuilder;
	@Autowired
	protected IChainBuilder chainBuilder;
	@Autowired
	protected StepBuilder stepBuilder;
	@Autowired
	protected IProcessorBuilder processorBuilder;

	@Override
	public <A, B> IChain<A, B> createChain(Function<A, B> func) {
		return chainBuilder.createChain(func);
	}

	@Override
	public <A, B> IChain<A, B> createChain(IProcessor<A, B> processor) {
		return chainBuilder.createChain(processor);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(Function<A, B> func1, Function<B, C> func2) {
		return chainBuilder.createChain(func1, func2);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IProcessor<A, B> processor1, IProcessor<B, C> processor2) {
		return chainBuilder.createChain(processor1, processor2);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IProcessor<A, B> processor, IChain<B, C> chain) {
		return chainBuilder.createChain(processor, chain);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IChain<A, B> chain1, IChain<B, C> chain2) {
		return chainBuilder.createChain(chain1, chain2);
	}

	@Override
	public <A, B, C> IChain<A, C> createChain(IChain<A, B> chain, IProcessor<B, C> processor) {
		return chainBuilder.createChain(chain, processor);
	}

	@Override
	public <T> IStep<T> createStep() {

		return stepBuilder.createStep();
	}

	@Override
	public <T> IStep<T> createStep(int parallelCount) {
		return stepBuilder.createStep(parallelCount);
	}

	@Override
	public <T> IStep<T> createStep(String parallelCountConfigName) {
		return stepBuilder.createStep(parallelCountConfigName);
	}

	@Override
	public <I> IProcessor<I, Boolean> createProcessor(Predicate<I> predicate) {

		return processorBuilder.createProcessor(predicate);
	}

	@Override
	public <I> IProcessor<I, Boolean> createProcessor(Consumer<I> consumer) {
		return processorBuilder.createProcessor(consumer);
	}

	@Override
	public <I, O> IProcessor<I, O> createProcessor(Function<I, O> func) {

		return processorBuilder.createProcessor(func);
	}

	@Override
	public <A, B, C> IProcessor<A, C> createProcessor(IProcessor<A, B> first, IProcessor<B, C> second) {

		return processorBuilder.createProcessor(first, second);
	}

	@Override
	public <A, B, C, D> IProcessor<A, D> createProcessor(IProcessor<A, B> processor1, IProcessor<B, C> processor2, IProcessor<C, D> processor3) {
		return processorBuilder.createProcessor(processor1, processor2, processor3);
	}

	@Override
	public <I, O> IConditionLoopProcessor<I, O> createConditionLoopProcessor(IProcessor<I, Boolean> validator, IProcessor<I, O> processor) {
		return processorBuilder.createConditionLoopProcessor(validator, processor);
	}

	@Override
	public <I, O> IConditionValidatorProcessor<I, O> createConditionValidatorProcessor(IProcessor<I, Boolean> validator, IProcessor<I, O> trueProcessor, IProcessor<I, O> falseProcessor) {
		return processorBuilder.createConditionValidatorProcessor(validator, trueProcessor, falseProcessor);
	}

	@Override
	public <I, O> IConditionValidatorSelectorProcessor<I, O> createConditionValidatorSelectorProcessor() {
		return processorBuilder.createConditionValidatorSelectorProcessor();
	}

	@Override
	public <I, S, O> IConditionSelectorProcessor<I, S, O> createConditionSelectorProcessor(IProcessor<I, S> selector) {
		return processorBuilder.createConditionSelectorProcessor(selector);
	}

	@Override
	public <I, S, O> IConditionSelectorProcessor<I, S, O> createConditionSelectorProcessor(IProcessor<I, S> selector, Map<S, IProcessor<I, O>> branchs) {
		return processorBuilder.createConditionSelectorProcessor(selector, branchs);
	}

	@Override
	public <A> UnaryContext<A> createContext(A context) {
		return contextBuilder.createContext(context);
	}

	@Override
	public <A, B> BinaryContext<A, B> createContext(A input, B output) {
		return contextBuilder.createContext(input, output);
	}

	@Override
	public <A, B, C> DataContext<A, B, C> createContext(A left, B middle, C right) {
		return contextBuilder.createContext(left, middle, right);
	}

	@Override
	public <A, B, C> DataContext<A, B, C> createContext() {
		return contextBuilder.createContext();
	}

	@Override
	public <I, O> IProcessor<I, O> createScheduledFixedDelayProcessor(IProcessor<I, O> processor, Long milliseconds) {
		return processorBuilder.createScheduledFixedDelayProcessor(processor, milliseconds);
	}

	@Override
	public <I, O> IProcessor<I, O> createScheduledFixedRateProcessor(IProcessor<I, O> processor, Long milliseconds) {
		return processorBuilder.createScheduledFixedRateProcessor(processor, milliseconds);
	}

}
