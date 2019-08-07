package com.github.zengfr.project.stepchain;

public interface IConditionLoopProcessor<I,O>  extends IProcessor<I,O> {
	void setValidator(IProcessor<I,Boolean> validator);
	void setProcessor(IProcessor<I,O> processor);
}
