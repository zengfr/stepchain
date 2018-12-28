package com.github.zengfr.project.stepchain.processor;

import java.util.function.Predicate;

import com.github.zengfr.project.stepchain.AbstractProcessor;

public class PredicateProcessor<I> extends AbstractProcessor<I, Boolean> {
	protected Predicate<I> predicate;

	public PredicateProcessor(Predicate<I> predicate) {
		this.predicate = predicate;
	}

	@Override
	protected Boolean execute(I context) throws Exception {
		return predicate.test(context);
	}

}
