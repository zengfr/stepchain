package com.github.zengfr.project.stepchain.context;

import com.github.zengfr.project.stepchain.Context;

public class TernaryContext<A, B, C>  implements Context {
	public A left;
	public B middle;
	public C right;
}
