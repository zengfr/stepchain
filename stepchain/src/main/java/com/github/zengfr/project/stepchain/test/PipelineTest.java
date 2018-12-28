package com.github.zengfr.project.stepchain.test;

import java.util.function.Function;

import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.ContextFactory;
import com.github.zengfr.project.stepchain.Pipeline;
import com.github.zengfr.project.stepchain.Step;
import com.github.zengfr.project.stepchain.context.UnaryContext;
import com.github.zengfr.project.stepchain.test.context.SetProductContext;
import com.github.zengfr.project.stepchain.test.context.SetProductDataMiddle;
import com.github.zengfr.project.stepchain.test.processor.DiscountProcessor;
import com.github.zengfr.project.stepchain.test.processor.FeeProcessor;
import com.github.zengfr.project.stepchain.test.processor.IncreaseProcessor;
import com.github.zengfr.project.stepchain.test.processor.InitProcessor;
import com.github.zengfr.project.stepchain.test.processor.TaxProcessor;

public class PipelineTest {
	public static void testPipeline(Pipeline pipeline) throws Exception {
		SetProductRequest req = new SetProductRequest();
		SetProductResponse resp = new SetProductResponse();
		SetProductDataMiddle middle = new SetProductDataMiddle();

		SetProductContext context = new SetProductContext(req, middle, resp);
		Step<SetProductContext> step = pipeline.createStep();
		step.put(new InitProcessor());
		step.put(new TaxProcessor());
		step.put(new FeeProcessor());
		step.put(new IncreaseProcessor());
		step.put(new DiscountProcessor());

		Function<SetProductContext, Boolean> func = (c) -> {
			c.middle.Price += 10;
			return true;
		};
		step.put(func);
		step.process(context);
		System.out.println(context.middle.Price);
	}

	public static void testPipeline2(Pipeline pipeline) throws Exception {
		Function<UnaryContext<Integer>, Boolean> func = (context) -> {
			if (context.context == null)
				context.context = 1;
			context.context += 1;
			return true;

		};
		UnaryContext<Integer> context = ContextFactory.createUnaryContext();
		Step<UnaryContext<Integer>> step = pipeline.createStep();
		Step<UnaryContext<Integer>> step2 = pipeline.createStep();
		Chain<UnaryContext<Integer>, Boolean> c2 = pipeline.createChain(func);
		// c2.next(func);

		step2.put(c2);
		step2.put(step);
		step2.put(func);

		step2.process(context);
		System.out.println(context.context);
	}
}
