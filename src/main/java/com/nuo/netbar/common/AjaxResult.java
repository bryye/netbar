package com.nuo.netbar.common;

import lombok.Data;

@Data
public class AjaxResult {
	private Integer code;
	private String message;
	private Object data;

	public static AjaxResult success() {
		return new AjaxResult(200, "", "");
	}

	public static AjaxResult success(String message, Object data) {
		return new AjaxResult(200, message, data);
	}

	public static AjaxResult success(Object data) {
		return new AjaxResult(200, "", data);
	}

	public static AjaxResult failed(String message) {
		return new AjaxResult(500, message, "");
	}

	public static AjaxResult failed(String message, Object data) {
		return new AjaxResult(500, message, data);
	}

	public AjaxResult(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public AjaxResult() {
		super();
	}

}
