package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.UsuarioDTO;
import com.alura.modelo.Usuario;
import com.alura.repositories.UsuarioRepository;
import com.alura.utilities.FromDTOtoModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;
	private FromDTOtoModel from;
	
	@Autowired
	public UsuarioController(UsuarioRepository usuarioRepository, FromDTOtoModel from) {
		this.usuarioRepository = usuarioRepository;
		this.from = from;
	}

	@PostMapping
	public void agregarUsuario(@RequestBody @Valid UsuarioDTO dto) {
		Usuario usuario = from.createUsuario(dto);
		usuarioRepository.save(usuario);
	}
}
