package com.github.zengfr.project.stepchain.context;

import com.github.zengfr.project.stepchain.IContext;

public class UnaryContext<A> implements IContext {
	public A context;
	public UnaryContext() {
		 
	}
	public UnaryContext(A context) {
		this.context =context;
		 
	}
}
