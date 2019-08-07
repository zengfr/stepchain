package com.github.zengfr.project.stepchain.processor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.zengfr.project.stepchain.IProcessor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class ScheduledFixedDelayProcessor<I,O> extends AbstractScheduledProcessor<I, O> {
	 
 public ScheduledFixedDelayProcessor(IProcessor<I,O> processor,long milliseconds) {
	super(processor, milliseconds);
 }
 
 protected void schedule(ScheduledExecutorService scheduledExecutorService,long milliseconds,Runnable runnable) {
	 scheduledExecutorService.scheduleWithFixedDelay(runnable,  milliseconds,  milliseconds,
				TimeUnit.MILLISECONDS);
 }
	@Override
	protected O execute(I context) throws Exception {
		 
		return this.processor.process(context);
	}
}
