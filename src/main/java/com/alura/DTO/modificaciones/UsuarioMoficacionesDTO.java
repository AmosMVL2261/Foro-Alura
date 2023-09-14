package com.alura.DTO.modificaciones;

import jakarta.validation.constraints.NotNull;

public class UsuarioMoficacionesDTO {
	@NotNull
	private Integer id;
	private String email;
	private String password;
	
	public UsuarioMoficacionesDTO() {
		
	}

	public UsuarioMoficacionesDTO(
		Integer id, 
		String email, 
		String password
	) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
}
