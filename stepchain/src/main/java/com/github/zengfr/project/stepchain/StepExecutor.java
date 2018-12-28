package com.github.zengfr.project.stepchain;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 * https://github.com/zengfr/stepchain-spring-boot-starter
 */
import java.util.Collection;

public interface StepExecutor {
	<I> Boolean execute(I context, Collection<StepProcessor<I>> processors) throws Exception;
}
