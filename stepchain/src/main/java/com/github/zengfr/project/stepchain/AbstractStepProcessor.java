package com.github.zengfr.project.stepchain;

/**
 * zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 * https://github.com/zengfr/stepchain-spring-boot-starter
 * 
 * @author zengfr QQ:362505707/1163551688 Email:zengfr3000@qq.com
 *         https://github.com/zengfr/stepchain-spring-boot-starter
 */
public abstract class AbstractStepProcessor<A> extends AbstractProcessor<A, Boolean>
		implements StepProcessor<A>, HandleExecute<A>, HandleException<A> {
	@Override
	public Boolean preExecute(A context) throws Exception {
		return true;
	}

	@Override
	public Boolean postExecute(A context) throws Exception {
		return true;
	}

	@Override
	public Boolean handleException(A context, Exception ex) throws Exception {
		return true;
	}

	@Override
	public Boolean process(A context) throws Exception {
		if (isEnabled()) {
			Boolean rtn = true;
			Exception ex = null;
			try {
				if (preExecute(context)) {
					rtn = execute(context);
					rtn = rtn && postExecute(context);
				}
			} catch (Exception e) {
				ex = e;
			} finally {
				rtn = handleException(context, ex) && rtn;
			}
		}
		return true;
	}

}
