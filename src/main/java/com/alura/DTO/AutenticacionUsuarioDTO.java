package com.alura.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AutenticacionUsuarioDTO {

	@NotEmpty @NotNull 
	private String email;
	@NotEmpty @NotNull 
	private String password;
	
	public AutenticacionUsuarioDTO() {
		
	}
	
	public AutenticacionUsuarioDTO(String email, String password) {
		this.email = email;
		this.password = password;
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
