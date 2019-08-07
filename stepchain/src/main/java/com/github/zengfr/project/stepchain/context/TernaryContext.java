package com.github.zengfr.project.stepchain.context;

import com.github.zengfr.project.stepchain.IContext;

public class TernaryContext<A, B, C>  implements IContext {
	public A left;
	public B middle;
	public C right;
	public TernaryContext() {
	}
	public TernaryContext(A left, B middle, C right) {
		this.left = left;
		this.middle = middle;
		this.right = right;
	}
}
