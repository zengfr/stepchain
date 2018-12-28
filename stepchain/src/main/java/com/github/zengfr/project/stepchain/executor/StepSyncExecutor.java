package com.github.zengfr.project.stepchain.executor;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.Processor;
import com.github.zengfr.project.stepchain.StepExecutor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class StepSyncExecutor<I> implements StepExecutor<I> {
	protected static Logger logger = LoggerFactory.getLogger(StepSyncExecutor.class);

	@Override
	public Boolean execute(I context, Collection<Processor<I, Boolean>> processors) throws Exception {

		Boolean results = true;
		if (!processors.isEmpty()) {
			for (Processor<I, Boolean> processor : processors) {
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
