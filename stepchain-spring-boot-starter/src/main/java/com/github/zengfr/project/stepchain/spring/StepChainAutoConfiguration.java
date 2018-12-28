package com.github.zengfr.project.stepchain.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
@Configuration
@ComponentScan(value = "com.github.zengfr.project.stepchain.impl")
public class StepChainAutoConfiguration {
}
