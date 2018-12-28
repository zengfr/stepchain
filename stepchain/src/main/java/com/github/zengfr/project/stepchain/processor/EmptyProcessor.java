package com.github.zengfr.project.stepchain.processor;

import com.github.zengfr.project.stepchain.AbstractProcessor;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public class EmptyProcessor<I, O> extends AbstractProcessor<I, O> {

	@Override
	protected O execute(I context) throws Exception {	 
		return null;
	}
	 
}
