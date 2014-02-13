package ool.com.ofpa.client;

public class OFCException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;

	public OFCException() {
		super();
	}
	
	public OFCException(String mes) {
		super(mes);
	}
	
	public OFCException(String mes, int statusCode) {
		super(mes);
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}

}
