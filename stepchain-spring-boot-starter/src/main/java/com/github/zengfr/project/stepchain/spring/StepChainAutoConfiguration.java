package com.github.zengfr.project.stepchain.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Configuration
@ComponentScan(value = { "com.github.zengfr.project.stepchain",
		"com.github.zengfr.project.stepchain.context",
		"com.github.zengfr.project.stepchain.chain",
		"com.github.zengfr.project.stepchain.step",
		"com.github.zengfr.project.stepchain.processor",
		"com.github.zengfr.project.stepchain.pipeline",
		"com.github.zengfr.project.stepchain.spring", 
		"com.github.zengfr.project.stepchain.config", })
public class StepChainAutoConfiguration {
}
