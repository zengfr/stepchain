package com.github.zengfr.project.stepchain.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.AbstractStepProcessor;
import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.StepProcessor;
import com.github.zengfr.project.stepchain.util.ProcessorUtil;
import com.github.zengfr.project.stepchain.util.StepProcessorUtil;
import com.github.zengfr.project.stepchain.Step;
import com.github.zengfr.project.stepchain.StepExecutor;
import com.google.common.collect.Lists;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
class StepImpl<I> extends AbstractStepProcessor<I> implements Step<I> {
	protected static Logger logger = LoggerFactory.getLogger(StepImpl.class);
	protected StepExecutor stepExecutor;
	protected List<StepProcessor<I>> childProcessors = Lists.newArrayList();

	public StepImpl() {
		init(-1);
	}

	public StepImpl(int parallelCount) {
		init(parallelCount);
	}

	protected void init(int parallelCount) {
		if (parallelCount <= 1) {
			stepExecutor = new StepSyncExecutor();
		} else {
			stepExecutor = new StepAsyncExecutor(parallelCount);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		childProcessors.clear();
		super.finalize();
	}

	@Override
	public Boolean execute(I context) throws Exception {
		return stepExecutor.execute(context, childProcessors);
	}

	@Override
	public void put(StepProcessor<I> processor) {
		childProcessors.add(processor);
	}

	@Override
	public void put(Processor<I, Boolean> processor) {
		put(StepProcessorUtil.create(processor));
	}

	@Override
	public void put(StepProcessor<I>... processorArray) {
		if (processorArray != null) {
			for (StepProcessor<I> processor : processorArray) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Collection<StepProcessor<I>> processors) {
		childProcessors.addAll(processors);
	}

	@Override
	public void put(Chain<I, Boolean> chain) {
		put((Processor<I, Boolean>) chain);
	}

	@Override
	public void put(Function<I, Boolean> func) {
		put(ProcessorUtil.create(func));
	}

	@Override
	public void put(Processor<I, Boolean>... processorArray) {
		if (processorArray != null) {
			for (Processor<I, Boolean> processor : processorArray) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Chain<I, Boolean>... processorArray) {
		if (processorArray != null) {
			for (Chain<I, Boolean> processor : processorArray) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Function<I, Boolean>... processorArray) {
		if (processorArray != null) {
			for (Function<I, Boolean> processor : processorArray) {
				put(processor);
			}
		}
	}

}
