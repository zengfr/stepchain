package com.github.zengfr.project.stepchain.step;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.AbstractStepProcessor;
import com.github.zengfr.project.stepchain.IChain;
import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.IStep;
import com.github.zengfr.project.stepchain.IStepExecutor;
import com.github.zengfr.project.stepchain.IStepProcessor;
import com.github.zengfr.project.stepchain.executor.StepAsyncExecutor;
import com.github.zengfr.project.stepchain.executor.StepSyncExecutor;
import com.github.zengfr.project.stepchain.processor.ConsumerProcessor;
import com.github.zengfr.project.stepchain.processor.PredicateProcessor;
import com.github.zengfr.project.stepchain.util.ProcessorUtil;
import com.google.common.collect.Lists;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
class Step<I> extends AbstractStepProcessor<I> implements IStep<I> {
	protected static Logger logger = LoggerFactory.getLogger(Step.class);
	protected IStepExecutor<I> stepExecutor;
	protected List<IProcessor<I, Boolean>> childProcessors = Lists.newArrayList();

	public Step() {
		init(-1);
	}

	public Step(int parallelCount) {
		init(parallelCount);
	}

	protected void init(int parallelCount) {
		if (parallelCount <= 1) {
			stepExecutor = new StepSyncExecutor<I>();
		} else {
			stepExecutor = new StepAsyncExecutor<I>(parallelCount);
		}
	}

	@Override
	public void clear() {
		childProcessors.clear();
	}

	@Override
	public Boolean execute(I context) throws Exception {
		return stepExecutor.execute(context, childProcessors);
	}

	@Override
	public void put(List<IProcessor<I, Boolean>> processors) {
		if (processors != null) {
			for (IProcessor<I, Boolean> processor : processors) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Collection<IProcessor<I, Boolean>> processors) {
		if (processors != null) {
			for (IProcessor<I, Boolean> processor : processors) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Iterable<IProcessor<I, Boolean>> processors) {
		if (processors != null) {
			for (IProcessor<I, Boolean> processor : processors) {
				put(processor);
			}
		}
	}

	@Override
	public void put(IProcessor<I, Boolean> processor) {
		childProcessors.add(processor);
	}

	@Override
	public void put(IProcessor<I, Boolean>... processorArray) {
		if (processorArray != null) {
			for (IProcessor<I, Boolean> processor : processorArray) {
				put(processor);
			}
		}
	}

	@Override
	public void put(IChain<I, Boolean> chain) {
		put((IProcessor<I, Boolean>) chain);
	}

	@Override
	public void put(IChain<I, Boolean>... chainArray) {
		if (chainArray != null) {
			for (IChain<I, Boolean> chain : chainArray) {
				put(chain);
			}
		}
	}

	@Override
	public void put(Function<I, Boolean> func) {
		put(ProcessorUtil.create(func));
	}

	@Override
	public void put(Function<I, Boolean>... funcArray) {
		if (funcArray != null) {
			for (Function<I, Boolean> func : funcArray) {
				put(func);
			}
		}
	}

	@Override
	public void put(IStep<I> step) {
		put((IProcessor<I, Boolean>) step);
	}

	@Override
	public void put(IStep<I>... stepArray) {
		if (stepArray != null) {
			for (IStep<I> step : stepArray) {
				put(step);
			}
		}
	}

	@Override
	public void put(IStepProcessor<I> processor) {
		put((IProcessor<I, Boolean>) processor);
	}

	@Override
	public void put(IStepProcessor<I>... processorArray) {
		if (processorArray != null) {
			for (IProcessor<I, Boolean> processor : processorArray) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Predicate<I> predicate) {
		put(new PredicateProcessor<I>(predicate));
	}

	@Override
	public void put(Predicate<I>... predicateArray) {
		if (predicateArray != null) {
			for (Predicate<I> predicate : predicateArray) {
				put(predicate);
			}
		}
	}

	@Override
	public void put(Consumer<I> consumer) {
		put(new ConsumerProcessor<I>(consumer));

	}

	@Override
	public void put(Consumer<I>... consumerArray) {
		if (consumerArray != null) {
			for (Consumer<I> consumer : consumerArray) {
				put(consumer);
			}
		}
	}
}
