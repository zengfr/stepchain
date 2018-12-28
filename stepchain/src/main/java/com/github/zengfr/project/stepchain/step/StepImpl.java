package com.github.zengfr.project.stepchain.step;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.AbstractStepProcessor;
import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.Step;
import com.github.zengfr.project.stepchain.StepExecutor;
import com.github.zengfr.project.stepchain.StepProcessor;
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
class StepImpl<I> extends AbstractStepProcessor<I> implements Step<I> {
	protected static Logger logger = LoggerFactory.getLogger(StepImpl.class);
	protected StepExecutor<I> stepExecutor;
	protected List<Processor<I, Boolean>> childProcessors = Lists.newArrayList();

	public StepImpl() {
		init(-1);
	}

	public StepImpl(int parallelCount) {
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
	public void put(List<Processor<I, Boolean>> processors) {
		if (processors != null) {
			for (Processor<I, Boolean> processor : processors) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Collection<Processor<I, Boolean>> processors) {
		if (processors != null) {
			for (Processor<I, Boolean> processor : processors) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Iterable<Processor<I, Boolean>> processors) {
		if (processors != null) {
			for (Processor<I, Boolean> processor : processors) {
				put(processor);
			}
		}
	}

	@Override
	public void put(Processor<I, Boolean> processor) {
		childProcessors.add(processor);
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
	public void put(Chain<I, Boolean> chain) {
		put((Processor<I, Boolean>) chain);
	}

	@Override
	public void put(Chain<I, Boolean>... chainArray) {
		if (chainArray != null) {
			for (Chain<I, Boolean> chain : chainArray) {
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
	public void put(Step<I> step) {
		put((Processor<I, Boolean>) step);
	}

	@Override
	public void put(Step<I>... stepArray) {
		if (stepArray != null) {
			for (Step<I> step : stepArray) {
				put(step);
			}
		}
	}

	@Override
	public void put(StepProcessor<I> processor) {
		put((Processor<I, Boolean>) processor);
	}

	@Override
	public void put(StepProcessor<I>... processorArray) {
		if (processorArray != null) {
			for (Processor<I, Boolean> processor : processorArray) {
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
