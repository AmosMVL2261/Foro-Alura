package com.alura.DTO.modificaciones;

import jakarta.validation.constraints.NotNull;

public class RespuestaModificacionesDTO {
	@NotNull
	private Integer id;
	private String mensaje;
	private Boolean solucion = false;
	
	public RespuestaModificacionesDTO() {
		
	}
	
	public RespuestaModificacionesDTO(
			Integer id, 
			String mensaje,
			Boolean solucion
	) {
		this.id = id;
		this.mensaje = mensaje;
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

	public Boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}
	
}
