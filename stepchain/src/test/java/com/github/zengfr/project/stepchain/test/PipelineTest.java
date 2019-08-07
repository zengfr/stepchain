package com.github.zengfr.project.stepchain.test;

import java.util.function.Function;

import com.alibaba.fastjson.JSON;
import com.github.zengfr.project.stepchain.IChain;
import com.github.zengfr.project.stepchain.IConditionLoopProcessor;
import com.github.zengfr.project.stepchain.IConditionSelectorProcessor;
import com.github.zengfr.project.stepchain.IPipeline;
import com.github.zengfr.project.stepchain.IProcessor;
import com.github.zengfr.project.stepchain.IStep;
import com.github.zengfr.project.stepchain.context.UnaryContext;
import com.github.zengfr.project.stepchain.test.context.SetProductContext;
import com.github.zengfr.project.stepchain.test.context.SetProductDataMiddle;
import com.github.zengfr.project.stepchain.test.processor.DiscountProcessor;
import com.github.zengfr.project.stepchain.test.processor.FeeProcessor;
import com.github.zengfr.project.stepchain.test.processor.IncreaseProcessor;
import com.github.zengfr.project.stepchain.test.processor.InitProcessor;
import com.github.zengfr.project.stepchain.test.processor.TaxProcessor;

public class PipelineTest {
	public static void testPipeline(IPipeline pipeline) throws Exception {
		SetProductRequest req = new SetProductRequest();
		SetProductResponse resp = new SetProductResponse();
		SetProductDataMiddle middle = new SetProductDataMiddle();

		SetProductContext context = new SetProductContext(req, middle, resp);
		IStep<SetProductContext> step = pipeline.createStep();
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

	public static void testPipeline2(IPipeline pipeline) throws Exception {
		Function<UnaryContext<Integer>, Boolean> func = (context) -> {
			if (context.context == null)
				context.context = 1;
			context.context += 1;
			return true;

		};
		Function<UnaryContext<Integer>, String> func3 = (context) -> {
			if (context.context == null)
				context.context = 1;
			context.context += 1;
			return JSON.toJSONString(context.context);

		};
		UnaryContext<Integer> context = pipeline.createContext(12345678);
		IStep<UnaryContext<Integer>> step = pipeline.createStep();
		IStep<UnaryContext<Integer>> step2 = pipeline.createStep();
		IChain<UnaryContext<Integer>, Boolean> c2 = pipeline.createChain(func);
		 
		IChain<UnaryContext<Integer>, String> c3 = pipeline.createChain(func3);
		Function<String, Integer> func4 = null;
		Function<Integer, String> func5 = null;
		Function<String, Boolean> func6 = null;
		IChain<String,Integer > c4 = pipeline.createChain(func4);
		IChain<Integer, String> c5 = pipeline.createChain(func5);
		IChain<String, Boolean> c6 = pipeline.createChain(func6);
		IChain<UnaryContext<Integer>, Boolean> c7 = c3.next(c4).next(c5).next(c6);
		
		step2.put(c2);
		step2.put(step);
		step2.put(func);
		//step2.put(c7);
		
		step2.process(context);
		System.out.println(context.context);
	}

	public static void testPipeline3(IPipeline pipeline) throws Exception {
		IProcessor<String, String> selector = null;
		IProcessor<String, Boolean> validator = null;

		IProcessor<String, String> processor = null;
		IProcessor<String, String> first = null;
		IProcessor<String, String> second = null;

		IConditionSelectorProcessor<String, Boolean, String> p3 = pipeline.createConditionValidatorProcessor(validator, first, second);
		IConditionLoopProcessor<String, String> p2 = pipeline.createConditionLoopProcessor(validator, processor);

		IConditionSelectorProcessor<String, String, String> p1 = pipeline.createConditionSelectorProcessor(selector);
	}
}
