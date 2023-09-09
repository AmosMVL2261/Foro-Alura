package com.alura.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CursoDTO {
	private Integer id;
	private String nombre;
	private String categoria;
	
	public CursoDTO() {
		
	}
	
	public CursoDTO(Integer id, @NotEmpty @NotNull String nombre, @NotEmpty @NotNull String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CursoDTO [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + "]";
	}
	
}
