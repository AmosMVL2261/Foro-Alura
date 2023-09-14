package com.alura.DTO.salida;

import java.time.LocalDateTime;

import com.alura.modelo.StatusTopico;
import com.alura.modelo.Topico;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TopicoSalidaDTO {
	@NotNull 
	private Integer id;
	@NotEmpty @NotNull 
	private String titulo;
	@NotEmpty @NotNull 
	private String mensaje;
	private LocalDateTime fechaDeCreacion = LocalDateTime.now();
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@NotNull 
	private Integer autor;
	@NotNull 
	private Integer curso;
	private Integer respuestas;
	
	public TopicoSalidaDTO() {
		
	}
	
	public TopicoSalidaDTO(
		Integer id, 
		String titulo, 
		String mensaje, 
		LocalDateTime fechaDeCreacion, 
		StatusTopico status,
		Integer autor, 
		Integer curso, 
		Integer respuestas
	) {
		this.id = id;
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.fechaDeCreacion = fechaDeCreacion;
		this.status = status;
		this.autor = autor;
		this.curso = curso;
		this.respuestas = respuestas;
	}

	public TopicoSalidaDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensaje = topico.getMensaje();
		this.fechaDeCreacion = topico.getfechaCreacion(); 
		this.status = topico.getStatus();
		this.autor = topico.getAutor().getId(); 
		this.curso = topico.getCurso().getId();
		this.respuestas = topico.getRespuestas().size();
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

	public LocalDateTime getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public Integer getAutor() {
		return autor;
	}

	public void setAutor(Integer autor) {
		this.autor = autor;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
	public Integer getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Integer respuestas) {
		this.respuestas = respuestas;
	}
	 
	@Override
	public String toString() {
		return "TopicoDTO [id=" + id + ", titulo=" + titulo + ", mensaje=" + mensaje + ", fechaDeCreacion="
				+ fechaDeCreacion + ", status=" + status + ", autor=" + autor + ", curso=" + curso + ""
				+ ", respuestas="+ respuestas + "]";
	}
	
}
