package ool.com.ofpa.agent;

public class OFPException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;

	public OFPException() {
		super();
	}
	
	public OFPException(String msg) {
		super(msg);
	}
	
	public OFPException(String msg, int statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}

}