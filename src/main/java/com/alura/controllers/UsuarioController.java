package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.UsuarioDTO;
import com.alura.DTO.modificaciones.UsuarioMoficacionesDTO;
import com.alura.DTO.salida.UsuarioSalidaDTO;
import com.alura.modelo.Usuario;
import com.alura.repositories.UsuarioRepository;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromDTOtoModel;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;
	private FromDTOtoModel fromDTOtoModel;
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public UsuarioController(
		UsuarioRepository usuarioRepository, 
		FromDTOtoModel from, 
		FromModelToDTO fromModelToDTO,
		Modificaciones modificaciones,
		Eliminaciones eliminaciones
	) {
		this.usuarioRepository = usuarioRepository;
		this.fromDTOtoModel = from;
		this.fromModelToDTO = fromModelToDTO;
		this.modificaciones = modificaciones;
		this.eliminaciones = eliminaciones;
	}

	@PostMapping
	public void agregarUsuario(@RequestBody @Valid UsuarioDTO dto) {
		Usuario usuario = fromDTOtoModel.createUsuario(dto);
		usuarioRepository.save(usuario);
	}
	
	@GetMapping
	public Page<UsuarioSalidaDTO> obtenerTodosLosUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
		Page<UsuarioSalidaDTO> lista = fromModelToDTO.obtenerTodosLosUsuarios(paginacion);
		return lista;
	}
	
	@GetMapping("/{id}")
	public UsuarioSalidaDTO obtenerUsuario(@PathVariable Integer id) {
		return fromModelToDTO.obtenerUsuario(id);
	}
	
	@PutMapping
	public void modificarUsuario(@RequestBody @Valid UsuarioMoficacionesDTO dto) {
		modificaciones.modificarUsuario(dto);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarUsuario(@PathVariable Integer id) {
		eliminaciones.eliminarUsuario(id);
	}
}
