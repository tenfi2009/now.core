/**
 * 
 */
package com.matrix.core.exception;

/**
 * @author rong yang
 *
 */
@SuppressWarnings("serial")
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	public BizException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
