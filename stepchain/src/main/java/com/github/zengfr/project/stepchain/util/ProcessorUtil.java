package com.github.zengfr.project.stepchain.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.processor.CompositeProcessor;
import com.github.zengfr.project.stepchain.processor.ConsumerProcessor;
import com.github.zengfr.project.stepchain.processor.FunctionProcessor;
import com.github.zengfr.project.stepchain.processor.PredicateProcessor;

public class ProcessorUtil {
	public static <A, B> IProcessor<A, B> create(Function<A, B> func) {
		return new FunctionProcessor<>(func);
	}

	public static <A> IProcessor<A, Boolean> create(Consumer<A> func) {
		return new ConsumerProcessor<>(func);
	}

	public static <A> IProcessor<A, Boolean> create(Predicate<A> func) {
		return new PredicateProcessor<>(func);
	}

	public static <A, B, C> IProcessor<A, C> create(Function<A, B> func1, Function<B, C> func2) {
		return new FunctionProcessor<>(func1.andThen(func2));
	}

	public static <A, B, C> IProcessor<A, C> create(IProcessor<A, B> processor1, IProcessor<B, C> processor2) {
		return new CompositeProcessor<>(processor1, processor2);
	}

	public static <A, B, C, D> IProcessor<A, D> create(IProcessor<A, B> processor1, IProcessor<B, C> processor2,
			IProcessor<C, D> processor3) {
		IProcessor<A, C> processor12 = create(processor1, processor2);
		return create(processor12, processor3);
	}

	public static <A, B, C, D, E> IProcessor<A, E> create(IProcessor<A, B> processor1, IProcessor<B, C> processor2,
			IProcessor<C, D> processor3, IProcessor<D, E> processor4) {
		IProcessor<A, C> processor12 = create(processor1, processor2);
		IProcessor<A, D> processor123 = create(processor12, processor3);
		return create(processor123, processor4);
	}
}
