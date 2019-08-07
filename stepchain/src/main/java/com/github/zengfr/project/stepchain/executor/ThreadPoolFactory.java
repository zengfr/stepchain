package com.github.zengfr.project.stepchain.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class ThreadPoolFactory {
	private static ThreadPoolExecutor global = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

	public static ThreadPoolExecutor smartCreateOrGet(int parallelCount) {
		if (parallelCount % 2 != 0) {
			return newFixedThreadPool(parallelCount);
		} else if (getGlobalQueueSize() < 2) {
			return getGlobalThreadPoolExecutor();
		} else {
			return newFixedThreadPool(parallelCount);
		}
	}

	public static ThreadPoolExecutor newFixedThreadPool(int parallelCount) {
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(parallelCount);
	}

	public static ThreadPoolExecutor getGlobalThreadPoolExecutor() {
		return global;
	}

	public static int getGlobalQueueSize() {
		return getGlobalThreadPoolExecutor().getQueue().size();
	}

	public static int getGlobalPoolSize() {
		return getGlobalThreadPoolExecutor().getPoolSize();
	}

	public static void setGlobalCorePoolSize(int corePoolSize) {
		getGlobalThreadPoolExecutor().setCorePoolSize(corePoolSize);
	}

	public static void setGlobalMaximumPoolSize(int maximumPoolSize) {
		getGlobalThreadPoolExecutor().setMaximumPoolSize(maximumPoolSize);
	}
}
