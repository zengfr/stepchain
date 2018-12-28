package com.github.zengfr.project.stepchain.processor;

import java.util.function.Consumer;

import com.github.zengfr.project.stepchain.AbstractProcessor;

public class ConsumerProcessor<I> extends AbstractProcessor<I, Boolean> {
	protected Consumer<I> consumer;
	public ConsumerProcessor(Consumer<I> consumer) {
		  this.consumer=consumer;
	}
	
	@Override
	protected Boolean execute(I context) throws Exception {
		consumer.accept(context);
		return true;
	}
	 
}

