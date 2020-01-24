package com.spk.demo.util;

import java.io.Serializable;

/**
 * @author sundaramp
 *
 * @param <T>
 */
public class APIResponse implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * status & message fields have not setter. They are assigned value when initial
	 * by APIStatus parameter
	 */
	private int status;
	private String message;
	private Object data;

	public APIResponse(APIStatus apiStatus, Object data) {

		if (apiStatus == null) {
			throw new IllegalArgumentException("APIStatus must not be null");
		}

		this.status = apiStatus.getCode();
		this.message = apiStatus.getDescription();
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
