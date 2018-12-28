package com.github.zengfr.project.stepchain.util;

import java.util.function.Function;

import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.impl.CompositeProcessor;
import com.github.zengfr.project.stepchain.impl.FunctionProcessor;

public class ProcessorUtil {
	public static <A, B> Processor<A, B> create(Function<A,B> func) {
		return new FunctionProcessor<>(func);
	}
	public static <A, B,C> Processor<A,C> create(Processor<A,B> processor1, Processor<B,C> processor2) {
		return new CompositeProcessor<>(processor1,processor2);
	}
	public static <A, B,C,D> Processor<A,D> create(Processor<A,B> processor1, Processor<B,C> processor2, Processor<C,D> processor3) {
		Processor<A,C> processor12 = create(processor1,processor2);
		return create(processor12,processor3);
	}
	public static <A, B,C,D,E> Processor<A,E> create(Processor<A,B> processor1, Processor<B,C> processor2, Processor<C,D> processor3, Processor<D,E> processor4) {
		Processor<A,C> processor12 = create(processor1,processor2);
		Processor<A,D> processor123 = create(processor12,processor3);
		return create(processor123,processor4);
	}
}
