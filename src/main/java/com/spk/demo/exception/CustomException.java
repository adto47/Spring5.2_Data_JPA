package com.spk.demo.exception;

import java.io.Serializable;
import java.util.List;

import com.spk.demo.util.APIStatus;
import com.spk.demo.util.Constant;

public class CustomException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	private APIStatus apiStatus;
	private List<Constant.ParamError> data;

	public CustomException(APIStatus apiStatus) {
		this.apiStatus = apiStatus;
	}

	/**
	 * This constructor is builded only for handling BAD REQUEST exception Careful
	 * when use it with other purpose ;)
	 *
	 * @param apiStatus
	 * @param data
	 */
	public CustomException(APIStatus apiStatus, List<Constant.ParamError> data) {
		this(apiStatus);
		this.data = data;
	}

	public CustomException(Throwable cause) {
		super(cause);
	}

	public APIStatus getApiStatus() {
		return apiStatus;
	}

	public List<Constant.ParamError> getData() {
		return data;
	}

}