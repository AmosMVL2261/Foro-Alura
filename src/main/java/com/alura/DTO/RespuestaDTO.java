package com.alura.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RespuestaDTO {
	private Integer id;
	private String mensaje;
	private Integer topico;
	private LocalDateTime fechaDeCreacion = LocalDateTime.now();
	private Integer autor;
	private Boolean solucion = false;
	
	public RespuestaDTO() {
		
	}
	
	public RespuestaDTO(
			Integer id, 
			@NotEmpty @NotNull String mensaje, 
			@NotEmpty @NotNull Integer topico, 
			LocalDateTime fechaDeCreacion, 
			@NotNull Integer autor,
			Boolean solucion
	) {
		this.id = id;
		this.mensaje = mensaje;
		this.topico = topico;
		this.fechaDeCreacion = fechaDeCreacion;
		this.autor = autor;
		this.solucion = solucion;
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
