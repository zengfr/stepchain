
package com.github.zengfr.project.stepchain.test;

import java.util.function.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.Pipeline;
import com.github.zengfr.project.stepchain.Step;
import com.github.zengfr.project.stepchain.context.ContextBuilder;
import com.github.zengfr.project.stepchain.context.UnaryContext;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StepChainTestApplication.class)
public class StepChainSpringBootTest {
	@Autowired
	protected Pipeline pipeline;

	@Test
	public void testPipeline() throws Exception {
		Function<UnaryContext<Integer>, Boolean> func = new Function<UnaryContext<Integer>, Boolean>() {

			@Override
			public Boolean apply(UnaryContext<Integer> context) {
				if (context.context == null)
					context.context = 1;
				context.context += 1;
				return true;
			}
		};
		UnaryContext<Integer> context = ContextBuilder.createUnary();
		Step<UnaryContext<Integer>> step = pipeline.createStep();
		Step<UnaryContext<Integer>> step2 = pipeline.createStep();
		Chain<UnaryContext<Integer>, Boolean> c2 = pipeline.createChain(func);
		//c2.next(func);

		step2.put(c2);
		step2.put(step);
		step2.put(func);

		step2.process(context);
		System.out.println(context.context);
	}

}
