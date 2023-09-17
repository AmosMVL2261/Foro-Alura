package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.AutenticacionUsuarioDTO;
import com.alura.infra.security.DatosJWTtoken;
import com.alura.infra.security.TokenService;
import com.alura.modelo.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
	
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;
	
	@Autowired
	public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<DatosJWTtoken> autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO dto) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
		var usuarioAutenticado = authenticationManager.authenticate(authToken);
		//getPrincipal es para obtener al usuario que ya fue autenticado
		String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
		return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
	}
}
