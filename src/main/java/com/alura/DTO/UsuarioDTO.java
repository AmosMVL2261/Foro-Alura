package com.alura.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {
	private Integer id;
	@NotEmpty @NotNull 
	private String nombre;
	@NotEmpty @NotNull 
	private String email;
	@NotEmpty @NotNull 
	private String password;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(
		Integer id, 
		String nombre, 
		String email, 
		String password
	) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + "]";
	}
	
}
