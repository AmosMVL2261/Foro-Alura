package com.alura.infra.errores;

import org.springframework.validation.FieldError;

//Clase usada para transmitirle al usuario los campos faltantes que generan un error al ser necesarios
public record DatosErrorValidacion(String campo, String error) {
	
	public DatosErrorValidacion(FieldError error) {
		this(error.getField(), error.getDefaultMessage());
	}
	
}
