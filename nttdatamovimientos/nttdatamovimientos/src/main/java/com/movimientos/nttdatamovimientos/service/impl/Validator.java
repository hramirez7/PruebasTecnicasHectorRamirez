package com.movimientos.nttdatamovimientos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.movimientos.nttdatamovimientos.dto.CuentaRequest;
import com.movimientos.nttdatamovimientos.model.ValidationError;
import com.movimientos.nttdatamovimientos.service.IValidator;

@Component
public class Validator implements IValidator<CuentaRequest> {
    
    @Override
	public List<ValidationError> validate(CuentaRequest cuenta) {
		List<ValidationError> errors = new ArrayList<>();

		validateField(cuenta.getNumeroCuenta(), "NumeroCuenta", "USR001", val -> val != null && !val.isEmpty(),
				"La {0} no puede ser nula o vacía", errors);

	/* 	validateField(usuario.getNombre(), "nombre", "USR002", val -> val != null && !val.isEmpty(),
				"El {0} no puede ser nulo o vacío", errors);

		validateField(usuario.getApellido(), "apellido", "USR003", val -> val != null && !val.isEmpty(),
				"El {0} no puede ser nulo o vacío", errors);

		validateField(usuario.getEmail(), "email", "USR004",
				val -> val != null && val.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"),
				"El {0} debe ser un correo electrónico válido", errors);

		validateField(usuario.getTelefono(), "teléfono", "USR005", val -> val != null && val.matches("\\d{9,15}"),
				"El {0} debe contener entre 9 y 15 dígitos", errors);

		validateField(usuario.getEstado(), "estado", "USR006", val -> val != null && !val.isEmpty(),
				"El {0} no puede ser nulo o vacío", errors);*/

		return errors;
	}

	public static <T> void validateField(T value, String fieldName, String code, Predicate<T> condition,
			String errorMessage, List<ValidationError> errors) {
		if (!condition.test(value)) {
			String formattedMessage = errorMessage.replace("{0}", fieldName);
			errors.add(new ValidationError(fieldName, formattedMessage, code));
		}
	}

}
