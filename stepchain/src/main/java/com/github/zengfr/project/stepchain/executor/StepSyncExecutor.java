package com.github.zengfr.project.stepchain.executor;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.IStepExecutor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class StepSyncExecutor<I> implements IStepExecutor<I> {
	protected static Logger logger = LoggerFactory.getLogger(StepSyncExecutor.class);

	@Override
	public Boolean execute(I context, Collection<IProcessor<I, Boolean>> processors) throws Exception {

		Boolean results = true;
		if (!processors.isEmpty()) {
			for (IProcessor<I, Boolean> processor : processors) {
				if (processor.isEnabled()) {
					results = results && processor.process(context);
				} else {
					logger.info(
							String.format("processor:%s,%s", processor.getClass().getName(), processor.isEnabled()));
				}
				if (!results) {
					break;
				}
			}
		}
		return results;
	}

}
