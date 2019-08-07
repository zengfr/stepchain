package com.github.zengfr.project.stepchain;

import com.github.zengfr.project.stepchain.context.BinaryContext;
import com.github.zengfr.project.stepchain.context.DataContext;
import com.github.zengfr.project.stepchain.context.UnaryContext;

public interface IContextBuilder {
	public <A> UnaryContext<A> createContext(A context);

	public <A, B> BinaryContext<A, B> createContext(A input, B output);

	public <A, B, C> DataContext<A, B, C> createContext();

	public <A, B, C> DataContext<A, B, C> createContext(A left, B middle, C right);
}
