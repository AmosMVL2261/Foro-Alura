package com.alura.DTO;

import java.time.LocalDateTime;

import com.alura.modelo.Respuesta;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RespuestaDTO {
	private Integer id;
	@NotEmpty @NotNull
	private String mensaje;
	@NotNull 
	private Integer topico;
	private LocalDateTime fechaDeCreacion = LocalDateTime.now();
	@NotNull
	private Integer autor;
	private Boolean solucion = false;
	
	public RespuestaDTO() {
		
	}
	
	public RespuestaDTO(
			Integer id, 
			String mensaje, 
			Integer topico, 
			LocalDateTime fechaDeCreacion, 
			Integer autor,
			Boolean solucion
	) {
		this.id = id;
		this.mensaje = mensaje;
		this.topico = topico;
		this.fechaDeCreacion = fechaDeCreacion;
		this.autor = autor;
		this.solucion = solucion;
	}

	public RespuestaDTO(Respuesta respuesta) {
		this.id = respuesta.getId();
		this.mensaje = respuesta.getMensaje();
		this.topico = respuesta.getTopico().getId(); 
		this.fechaDeCreacion = respuesta.getfechaCreacion(); 
		this.autor = respuesta.getAutor().getId(); 
		this.solucion = respuesta.getSolucion();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getTopico() {
		return topico;
	}

	public void setTopico(Integer topico) {
		this.topico = topico;
	}

	public LocalDateTime getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public Integer getAutor() {
		return autor;
	}

	public void setAutor(Integer autor) {
		this.autor = autor;
	}

	public Boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}

	@Override
	public String toString() {
		return "RespuestaDTO [id=" + id + ", mensaje=" + mensaje + ", topico=" + topico + ", fechaDeCreacion="
				+ fechaDeCreacion + ", autor=" + autor + ", solucion=" + solucion + "]";
	}
	
}
