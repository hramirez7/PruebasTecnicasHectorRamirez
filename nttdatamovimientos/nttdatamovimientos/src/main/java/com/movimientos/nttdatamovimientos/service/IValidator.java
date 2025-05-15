package com.movimientos.nttdatamovimientos.service;

import java.util.List;

import com.movimientos.nttdatamovimientos.model.ValidationError;


public interface IValidator<T> {
	List<ValidationError> validate(T object);
}