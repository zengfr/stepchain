package com.github.zengfr.project.stepchain.processor;

import java.util.Map;

import com.github.zengfr.project.stepchain.AbstractProcessor;
import com.github.zengfr.project.stepchain.IConditionSelectorProcessor;
import com.github.zengfr.project.stepchain.IProcessor;
import com.google.common.collect.Maps;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class ConditionSelectorProcessor<I, S, O> extends AbstractProcessor<I, O> implements IConditionSelectorProcessor<I, S, O> {
	private IProcessor<I, S> selector;
	private Map<S, IProcessor<I, O>> branchs;
	private S defaultBranch;

	public ConditionSelectorProcessor() {
		this.branchs = Maps.newHashMap();
	}

	@Override
	public void setDefaultBranch(S key) {
		this.defaultBranch = key;
	}

	@Override
	public void setSelector(IProcessor<I, S> selector) {
		this.selector = selector;
	}

	@Override
	public void setBranch(S key, IProcessor<I, O> processor) {
		this.branchs.put(key, processor);
	}

	@Override
	protected O execute(I context) throws Exception {
		S s = selector.process(context);
		if (!branchs.containsKey(s)) {
			s = defaultBranch;
		}
		return branchs.get(s).process(context);

	}

}
