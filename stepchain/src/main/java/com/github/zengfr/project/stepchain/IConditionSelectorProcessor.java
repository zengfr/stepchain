package com.github.zengfr.project.stepchain;

public interface IConditionSelectorProcessor<I,S,O>  extends IProcessor<I, O> {
       void setSelector(IProcessor<I,S> selector);
       void setBranch(S key, IProcessor<I, O> processor);
       void setDefaultBranch(S key);
}