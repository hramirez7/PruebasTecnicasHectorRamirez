package com.movimientos.nttdatamovimientos.model;

import lombok.Data;

@Data
public class EResponse<T> {
 
	private T body;
	private String error;
	private String code;
 
	public EResponse() {
	}
 
	public EResponse(T body, String error, String code) {
		this.body = body;
		this.error = error;
		this.code = code;
	}
 
	
}