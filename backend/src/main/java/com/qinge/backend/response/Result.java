package com.qinge.backend.response;

public class Result {


	/**
	 * 消息码
	 */
	private Integer code;


	/**
	 * 消息
	 */
	private String msg;


	/**
	 * 携带数据
	 */
	private Object data;
	public static Result success(Object data) {
		return new Result(1, "success", data);
	}
	public static Result success() {
		return new Result(1, "success", null);
	}
	public static Result error(String msg) {
		return new Result(0, msg, null);
	}


	public Result() {
	}

	public Result(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return this.data;
	}
}