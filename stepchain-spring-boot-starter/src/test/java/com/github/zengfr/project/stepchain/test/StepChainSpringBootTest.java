
package com.github.zengfr.project.stepchain.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.zengfr.project.stepchain.IPipeline;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StepChainTestApplication.class)
public class StepChainSpringBootTest {
	@Autowired
	protected IPipeline pipeline;
	@Test
	public void testPipeline() throws Exception {
		PipelineTest.testPipeline(pipeline);
	}
	@Test
	public void testPipeline2() throws Exception {
		PipelineTest.testPipeline2(pipeline);
	}
}
