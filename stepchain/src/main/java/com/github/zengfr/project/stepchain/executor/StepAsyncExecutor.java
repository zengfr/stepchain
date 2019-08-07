package com.github.zengfr.project.stepchain.executor;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.IStepExecutor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class StepAsyncExecutor<I> implements IStepExecutor<I> {
	protected static Logger logger = LoggerFactory.getLogger(StepAsyncExecutor.class);
	protected int parallelCount = -1;

	public StepAsyncExecutor(int parallelCount) {
		this.parallelCount = parallelCount;
	}

	@Override
	public Boolean execute(I context, Collection<IProcessor<I, Boolean>> processors) throws InterruptedException, ExecutionException {
		Boolean results = true;

		if (!processors.isEmpty()) {
			ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.newFixedThreadPool(parallelCount);
			CompletionService<Boolean> cService = new ExecutorCompletionService<>(threadPoolExecutor);
			results = executeByServiceThreadPool(cService, context, processors);
			threadPoolExecutor.shutdown();
			threadPoolExecutor.awaitTermination(11, TimeUnit.HOURS);
		}
		return results;
	}

	protected Boolean executeByServiceThreadPool(CompletionService<Boolean> cService, I context, Collection<IProcessor<I, Boolean>> processors) throws InterruptedException, ExecutionException {
		Boolean results = true;
		for (IProcessor<I, Boolean> processor : processors) {
			cService.submit(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					if (processor.isEnabled()) {
						try {
							return processor.process(context);
						} catch (Exception ex) {
							logger.error("executeByServiceThreadPool", ex);
							return false;
						}
					} else {
						logger.info(String.format("processor:%s,%s", processor.getClass().getName(), processor.isEnabled()));
					}
					return true;
				}
			});
		}
		for (int i = 0; i < processors.size(); i++) {
			results = results && cService.take().get();
			if (!results) {
				break;
			}
		}
		return results;
	}
}
