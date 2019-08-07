package com.github.zengfr.project.stepchain.processor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import com.github.zengfr.project.stepchain.AbstractProcessor;
import com.github.zengfr.project.stepchain.IProcessor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public abstract class AbstractScheduledProcessor<I, O> extends AbstractProcessor<I, O> {
	protected ScheduledExecutorService scheduledExecutorService;
	protected Boolean scheduled;
	protected Long milliseconds;
	protected IProcessor<I, O> processor;

	public AbstractScheduledProcessor(IProcessor<I, O> processor, long milliseconds) {
		this(processor, milliseconds, 2);
	}

	public AbstractScheduledProcessor(IProcessor<I, O> processor, long milliseconds, int corePoolSize) {
		this.milliseconds = milliseconds;
		this.processor = processor;
		this.scheduledExecutorService = Executors.newScheduledThreadPool(corePoolSize);

	}

	protected void schedule(I context) {
		if (!this.scheduled) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						execute(context);
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			};
			schedule(this.scheduledExecutorService, this.milliseconds, runnable);
			this.scheduled=true;
		}
	}

	protected abstract void schedule(ScheduledExecutorService scheduledExecutorService, long milliseconds, Runnable runnable);

	@Override
	protected O execute(I context) throws Exception {

		return this.processor.process(context);
	}
}
