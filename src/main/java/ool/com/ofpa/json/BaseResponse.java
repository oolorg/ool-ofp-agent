package ool.com.ofpa.json;

public class BaseResponse {
	private int status;
	private String message;

	public String getMessage() {
		return this.message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(final int status) {
		this.status = status;
	}
}
