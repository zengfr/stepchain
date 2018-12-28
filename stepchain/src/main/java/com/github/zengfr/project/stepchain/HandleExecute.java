package com.github.zengfr.project.stepchain;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public interface HandleExecute<I> {
	Boolean preExecute(I context) throws Exception;

	Boolean postExecute(I context) throws Exception;
}
