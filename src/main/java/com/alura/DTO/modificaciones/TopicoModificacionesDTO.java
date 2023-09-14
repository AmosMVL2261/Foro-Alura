package com.alura.DTO.modificaciones;

import com.alura.modelo.StatusTopico;

import jakarta.validation.constraints.NotNull;

public class TopicoModificacionesDTO {
	@NotNull 
	private Integer id;
	private String titulo;
	private String mensaje;
	private StatusTopico status;
	private Integer curso;
	
	public TopicoModificacionesDTO() {
		
	}
	
	public TopicoModificacionesDTO(
		Integer id, 
		String titulo, 
		String mensaje, 
		StatusTopico status,
		Integer curso
	) {
		this.id = id;
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.status = status;
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
}
