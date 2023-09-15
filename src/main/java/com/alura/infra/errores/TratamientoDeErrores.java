package com.alura.infra.errores;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamientoDeErrores {

	/*
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> tratarError404() {
		return ResponseEntity.notFound().build();
	}
	*/
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Void> tratarError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DatosErrorValidacion>> tratarError400(MethodArgumentNotValidException e) {
		List<DatosErrorValidacion> lista = e.getFieldErrors()
				.stream()
				.map(elemento ->  new DatosErrorValidacion(elemento))
				.toList();
		return ResponseEntity.badRequest().body(lista);
	}
}
