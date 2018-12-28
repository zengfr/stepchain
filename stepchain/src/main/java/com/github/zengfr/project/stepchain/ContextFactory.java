package com.github.zengfr.project.stepchain;

import com.github.zengfr.project.stepchain.context.BinaryContext;
import com.github.zengfr.project.stepchain.context.DataContext;
import com.github.zengfr.project.stepchain.context.TernaryContext;
import com.github.zengfr.project.stepchain.context.UnaryContext;

public class ContextFactory {
	public static <A> UnaryContext<A> createUnaryContext() {
		return new UnaryContext<>();
	}

	public static <A, B> BinaryContext<A, B> createBinaryContext() {
		return new BinaryContext<>();
	}

	public static <A, B, C> TernaryContext<A, B, C> createTernaryContext() {
		return new TernaryContext<>();
	}

	public static <A, B, C> DataContext<A, B, C> createDataContext(A a, B b, C c) {
		return new DataContext<>(a, b, c);
	}
}
