package com.github.zengfr.project.stepchain.test.context;

import com.github.zengfr.project.stepchain.context.DataContext;
import com.github.zengfr.project.stepchain.test.SetProductRequest;
import com.github.zengfr.project.stepchain.test.SetProductResponse;

public class SetProductContext extends DataContext<SetProductRequest, SetProductDataMiddle, SetProductResponse> {

	public SetProductContext(SetProductRequest a, SetProductDataMiddle b, SetProductResponse c) {
		super(a, b, c);
	}
	
}
