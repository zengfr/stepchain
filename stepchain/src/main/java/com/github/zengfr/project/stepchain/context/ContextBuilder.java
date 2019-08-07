package com.github.zengfr.project.stepchain.context;

import org.springframework.stereotype.Component;

import com.github.zengfr.project.stepchain.IContextBuilder;

@Component
public class ContextBuilder implements IContextBuilder {
	public   <A> UnaryContext<A> createContext(A context) {
		return new UnaryContext<>(context);
	}

	public   <A, B> BinaryContext<A, B> createContext(A input, B output) {
		return new BinaryContext<>(input,output);
	}
	public   <A, B, C> DataContext<A, B, C> createContext() {
		return new DataContext<>();
	}
	public   <A, B, C> DataContext<A, B, C> createContext(A left, B middle, C right) {
		return new DataContext<>(left,middle, right);
	}

	 
}
