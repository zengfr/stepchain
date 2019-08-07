package com.github.zengfr.project.stepchain.context;

public class DataContext<A, B, C> extends TernaryContext<A, B, C> {
	public DataContext() {
		super();
	}
	public DataContext(A left, B middle, C right) {
		super(left, middle, right);
	}
}
