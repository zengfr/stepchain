package com.github.zengfr.project.stepchain.util;

import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.StepProcessor;
import com.github.zengfr.project.stepchain.impl.StepProcessorDecorator;

public class StepProcessorUtil {
	public static <A> StepProcessor<A> create(Processor<A, Boolean> processor) {
		return new StepProcessorDecorator<A>(processor);
	}
 
	public static <A, B> StepProcessor<A> create(Processor<A, B> processor1, Processor<B, Boolean> processor2) {
		Processor<A, Boolean> processor12 = ProcessorUtil.create(processor1, processor2);
		return create(processor12);
	}
	 
	public static <A, B, C> StepProcessor<A> create(Processor<A, B> processor1, Processor<B, C> processor2,
			Processor<C, Boolean> processor3) {
		Processor<A, C> processor12 = ProcessorUtil.create(processor1, processor2);
		Processor<A, Boolean> processor123 = ProcessorUtil.create(processor12, processor3);
		return create(processor123);
	}

	public static <A, B, C, D> StepProcessor<A> create(Processor<A, B> processor1, Processor<B, C> processor2,
			Processor<C, D> processor3, Processor<D, Boolean> processor4) {
		Processor<A, C> processor12 = ProcessorUtil.create(processor1, processor2);
		Processor<A, D> processor123 = ProcessorUtil.create(processor12, processor3);
		Processor<A, Boolean> processor1234 = ProcessorUtil.create(processor123, processor4);
		return create(processor1234);
	}

	public static <A, B, C, D, E> StepProcessor<A> create(Processor<A, B> processor1, Processor<B, C> processor2,
			Processor<C, D> processor3, Processor<D, E> processor4, Processor<E, Boolean> processor5) {
		Processor<A, C> processor12 = ProcessorUtil.create(processor1, processor2);
		Processor<A, D> processor123 = ProcessorUtil.create(processor12, processor3);
		Processor<A, E> processor1234 = ProcessorUtil.create(processor123, processor4);
		Processor<A, Boolean> processor12345 = ProcessorUtil.create(processor1234, processor5);
		return create(processor12345);
	}
}
