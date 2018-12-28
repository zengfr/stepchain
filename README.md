# stepchain-spring-boot-starter
Java Pipeline Step Chain like Apache  Commons Chain and Commons Pipeline。
A popular technique for organizing the execution of complex processing flows is the "Chain of Responsibility" pattern。

https://github.com/zengfr/stepchain-spring-boot-starter/

[Repositories Central Sonatype Mvnrepository](https://mvnrepository.com/search?q=com.github.zengfr.project)
```
1、支持通用业务job、services子流程无限制拆分。
2、支持业务子流程串行化、业务子流程并行化，可配置化。
3、支持Config业务子流程开启或禁用、配置串行或并行以及并行数的统一配置。
4、支持业务流程以及子流程任意无限嵌套。
5、支持配置中心、缓存、统一数据接口、redis、Es、日志Trace等。
备注：只开源了通用部分(不影响使用),去除了和公司有关框架组件包括：配置中心、缓存中心、数据接口以及业务相关DataMiddle等部分API。
``` 
``` 
Maven Dependency:
Maven(Not Use Spring Boot):
<dependency>
 <groupId>com.github.zengfr.project</groupId>
 <artifactId>stepchain</artifactId>
 <version>0.0.5</version>
<dependency>
Maven(Use Spring Boot):
<dependency>
 <groupId>com.github.zengfr.project</groupId>
 <artifactId>stepchain-spring-boot-starter</artifactId>
 <version>0.0.5</version>
<dependency>
Gradle:
compile group: 'com.github.zengfr.project', name: 'stepchain', version: '0.0.5'
compile group: 'com.github.zengfr.project', name: 'stepchain-spring-boot-starter', version: '0.0.5'
``` 
interface Pipeline ChainBuilder StepBuilder Step Chain  [javadoc api文档](https://oss.sonatype.org/service/local/repositories/releases/archive/com/github/zengfr/project/stepchain/0.0.5/stepchain-0.0.5-javadoc.jar/!/index.html)
```java
public interface Step<I> extends StepProcessor<I> {
	void put(StepProcessor<I> processor);

	void put(StepProcessor<I>... processorArray);

	void put(Collection<StepProcessor<I>> processors);

	void put(Processor<I, Boolean> processor);

	void put(Processor<I, Boolean>... processorArray);

	void put(Chain<I, Boolean> chain);

	void put(Chain<I, Boolean>... processorArray);

	void put(Function<I, Boolean> func);

	void put(Function<I, Boolean>... processorArray);
}
public interface Chain<A, B> extends Processor<A, B> {
	<C> Chain<A, C> next(Processor<B, C> process);

	<C> Chain<A, C> next(Function<B, C> func);
}
public interface ChainBuilder {
	<A, B> Chain<A, B> createChain(Function<A, B> func);

	<A, B> Chain<A, B> createChain(Processor<A, B> processor);

	<A, B, C> Chain<A, C> createChain(Processor<A, B> processor1, Processor<B, C> processor2);
}
public interface StepBuilder {
	<T> Step<T> createStep();

	<T> Step<T> createStep(int parallelCount);

	<T> Step<T> createStep(String parallelCountConfigName);
}
```
[StepChainSpringBootTest.java](https://github.com/zengfr/stepchain-spring-boot-starter/blob/master/stepchain-spring-boot-starter/src/test/java/com/github/zengfr/project/stepchain/test/StepChainSpringBootTest.java)

[PipelineTest.java](https://github.com/zengfr/stepchain-spring-boot-starter/blob/master/stepchain/src/main/java/com/github/zengfr/project/stepchain/test/PipelineTest.java)
<br/>Demo&Test you can use AbstractProcessor AbstractStepProcessor
```java
import com.github.zengfr.project.stepchain
abstract class AbstractProcessor<I, O> implements Processor<I, O>{}
abstract class AbstractStepProcessor<A> extends AbstractProcessor<A, Boolean> implements StepProcessor<A>{}
```
```java
import com.github.zengfr.project.stepchain.Chain;
import com.github.zengfr.project.stepchain.Pipeline;
import com.github.zengfr.project.stepchain.Step;
import com.github.zengfr.project.stepchain.context.ContextBuilder;
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
    //Demo精简版 只开源了通用部分(不影响使用)
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
	step.put((c) -> {
		c.middle.Price += 10;
		return true;
	});
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
	UnaryContext<Integer> context = ContextBuilder.createUnaryContext();
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
```
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StepChainTestApplication.class)
public class StepChainSpringBootTest {
	@Autowired
	protected Pipeline pipeline;
	@Test
	public void testPipeline() throws Exception {
	PipelineTest.testPipeline(pipeline);
	}
	@Test
	public void testPipeline2() throws Exception {
	PipelineTest.testPipeline2(pipeline);
	}

```
