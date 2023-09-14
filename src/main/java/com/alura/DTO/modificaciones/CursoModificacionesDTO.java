package com.alura.DTO.modificaciones;

import com.alura.modelo.Curso;

import jakarta.validation.constraints.NotNull;

public class CursoModificacionesDTO {
	@NotNull
	private Integer id;
	private String nombre;
	private String categoria;
	
	public CursoModificacionesDTO() {
		
	}
	
	public CursoModificacionesDTO(Integer id, String nombre, String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	public CursoModificacionesDTO(Curso curso) {
		this.id = curso.getId();
		this.nombre = curso.getNombre();
		this.categoria = curso.getCategoria();
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
