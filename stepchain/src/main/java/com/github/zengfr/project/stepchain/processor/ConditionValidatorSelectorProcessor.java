package com.github.zengfr.project.stepchain.processor;

import java.util.Map;
import java.util.Map.Entry;

import com.github.zengfr.project.stepchain.AbstractProcessor;
import com.github.zengfr.project.stepchain.IConditionValidatorSelectorProcessor;
import com.github.zengfr.project.stepchain.IProcessor;
import com.google.common.collect.Maps;

public class ConditionValidatorSelectorProcessor<I, O> extends AbstractProcessor<I, O> implements IConditionValidatorSelectorProcessor<I, O> {
	private Map<IProcessor<I,Boolean>, Boolean> expects;
	private Map<IProcessor<I,Boolean>, IProcessor<I, O>> branchs;
	private IProcessor<I, O> defaultProcessor;

	public ConditionValidatorSelectorProcessor() {
		expects = Maps.newLinkedHashMap();
		branchs = Maps.newHashMap();
	}

	@Override
	public void setDefaultBranch(IProcessor<I, O> processor) {
		this.defaultProcessor = processor;
	}

	@Override
	public void setBranch(IProcessor<I,Boolean> validator, Boolean result, IProcessor<I, O> processor) {
		expects.put(validator, result);
		branchs.put(validator, processor);

	}

	@Override
	protected O execute(I context) throws Exception {
		for (Entry<IProcessor<I,Boolean>, Boolean> expect : expects.entrySet()) {
			if (expect.getKey().process(context).equals(expect.getValue())) {
				return branchs.get(expect.getKey()).process(context);
			}
		}
		return defaultProcessor.process(context);
	}

}
