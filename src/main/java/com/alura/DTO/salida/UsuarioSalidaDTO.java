package com.alura.DTO.salida;

import com.alura.modelo.Usuario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UsuarioSalidaDTO {
	private Integer id;
	private String nombre;
	private String email;
	
	public UsuarioSalidaDTO() {
		
	}

	public UsuarioSalidaDTO(
		@NotEmpty @NotNull String nombre,
		@NotEmpty @NotNull String email
	) {
		this.nombre = nombre;
		this.email = email;
	}
	
	public UsuarioSalidaDTO(Integer id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}
	
	public UsuarioSalidaDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.email = usuario.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
