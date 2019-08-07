package com.github.zengfr.project.stepchain;

public interface IConditionValidatorSelectorProcessor<I,O> extends IProcessor<I, O> {
   void setBranch(IProcessor<I, Boolean> validator,Boolean result,IProcessor<I, O> processor);
   void setDefaultBranch(IProcessor<I, O> processor);
}