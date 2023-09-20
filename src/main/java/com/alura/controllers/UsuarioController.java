package com.alura.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.DTO.UsuarioDTO;
import com.alura.DTO.modificaciones.UsuarioMoficacionesDTO;
import com.alura.DTO.salida.UsuarioSalidaDTO;
import com.alura.utilities.Agregaciones;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
	
	private Agregaciones agregaciones;
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public UsuarioController(
		Agregaciones agregaciones, 
		FromModelToDTO fromModelToDTO,
		Modificaciones modificaciones,
		Eliminaciones eliminaciones
	) {
		this.agregaciones = agregaciones;
		this.fromModelToDTO = fromModelToDTO;
		this.modificaciones = modificaciones;
		this.eliminaciones = eliminaciones;
	}

	@PostMapping
	public ResponseEntity<UsuarioSalidaDTO> agregarUsuario(@RequestBody @Valid UsuarioDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		UsuarioSalidaDTO nuevoUsuario = agregaciones.agregarUsuario(dto);
		URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
		return ResponseEntity.created(url).body(nuevoUsuario);
	}
	
	@GetMapping
	public ResponseEntity<Page<UsuarioSalidaDTO>> obtenerTodosLosUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
		Page<UsuarioSalidaDTO> lista = fromModelToDTO.obtenerTodosLosUsuarios(paginacion);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioSalidaDTO> obtenerUsuario(@PathVariable Integer id) {
		UsuarioSalidaDTO usuario = fromModelToDTO.obtenerUsuario(id);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping
	public ResponseEntity<UsuarioSalidaDTO> modificarUsuario(@RequestBody @Valid UsuarioMoficacionesDTO dto) {
		UsuarioSalidaDTO usuarioModificado = modificaciones.modificarUsuario(dto);
		return ResponseEntity.ok(usuarioModificado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
		eliminaciones.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
