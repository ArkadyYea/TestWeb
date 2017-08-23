package event.transactional;

import javax.servlet.AsyncContext;

public class Transporter {
	
	private String info;
	private AsyncContext asyncCtx;
	
	public Transporter() {}

	public Transporter(String info, AsyncContext asyncCtx) {
		this.info = info;
		this.asyncCtx = asyncCtx;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public AsyncContext getAsyncCtx() {
		return asyncCtx;
	}

	public void setAsyncCtx(AsyncContext asyncCtx) {
		this.asyncCtx = asyncCtx;
	}
	
}
