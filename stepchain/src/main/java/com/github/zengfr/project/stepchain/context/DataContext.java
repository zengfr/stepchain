package com.github.zengfr.project.stepchain.context;

public class DataContext<A, B, C> extends TernaryContext<A, B, C> {
	public DataContext(A a, B b, C c) {
		this.left = a;
		this.middle = b;
		this.right = c;
	}
}
