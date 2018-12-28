package com.github.zengfr.project.stepchain.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zengfr.project.stepchain.StepExecutor;
import com.github.zengfr.project.stepchain.StepProcessor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class StepSyncExecutor implements StepExecutor {
	protected static Logger logger = LoggerFactory.getLogger(StepSyncExecutor.class);

	@Override
	public <I> Boolean execute(I context, Collection<StepProcessor<I>> processors) throws Exception {

		Boolean results = true;
		if (!processors.isEmpty()) {
			for (StepProcessor<I> processor : processors) {
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
