package com.github.zengfr.project.stepchain;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface IProcessor<I, O> {
	Boolean isEnabled();

	O process(I context) throws Exception;
	 
}
  
