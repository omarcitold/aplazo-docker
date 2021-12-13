package com.example.demo.models;

import java.util.List;

public class ResponseVO {
	
	private Integer code;
	private String message;
	private List<OutputModel> output;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<OutputModel> getOutput() {
		return output;
	}
	public void setOutput(List<OutputModel> output) {
		this.output = output;
	}
	
	@Override
	public String toString() {
		return "ResponseVO [code=" + code + ", message=" + message + ", output=" + output + "]";
	}
}
