package com.github.zengfr.project.stepchain.processor;

import java.util.function.Predicate;

import com.github.zengfr.project.stepchain.AbstractProcessor;
/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
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
