package com.github.zengfr.project.stepchain.context;

public class ContextBuilder {
	public static <A> UnaryContext<A> createUnary() {
		return new UnaryContext<>();
	}

	public static <A, B> BinaryContext<A, B> createBinary() {
		return new BinaryContext<>();
	}

	public static <A, B, C> TernaryContext<A, B, C> createTernary() {
		return new TernaryContext<>();
	}
}
