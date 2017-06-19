package cn.ryan.xpath.exception;

public class ObjectCreateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -17246182447634582L;

	public ObjectCreateException(Throwable cause) {
		super(cause);
	}

	public ObjectCreateException(String message) {
		super(message);
	}

	public ObjectCreateException(String message, Throwable cause) {
		super(message, cause);
	}

	protected ObjectCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
