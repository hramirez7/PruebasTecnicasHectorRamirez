package com.movimientos.nttdatamovimientos.model;

import lombok.Data;

@Data
public class ValidationError {
	private String field;
	private String message;
	private String code;

	public ValidationError(String field, String message, String code) {
		this.field = field;
		this.message = message;
		this.code = code;
	}
}