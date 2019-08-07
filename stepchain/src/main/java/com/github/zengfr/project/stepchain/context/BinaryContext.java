package com.github.zengfr.project.stepchain.context;

import com.github.zengfr.project.stepchain.IContext;

public class BinaryContext<A, B> implements IContext {
	public A input;
	public B output;
	public BinaryContext() {
	}
	public BinaryContext(A input, B output) {
		this.input = input;
		this.output = output;
	}
}
