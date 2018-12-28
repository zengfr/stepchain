package com.github.zengfr.project.stepchain.impl;

import com.github.zengfr.project.stepchain.AbstractStepProcessor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class StepEmptyProcessor<I> extends AbstractStepProcessor<I> {

	@Override
	protected Boolean execute(I context) throws Exception {
		return true;
	}

}
