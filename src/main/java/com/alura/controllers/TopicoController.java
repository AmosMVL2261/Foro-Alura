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

import com.alura.DTO.TopicoDTO;
import com.alura.DTO.modificaciones.TopicoModificacionesDTO;
import com.alura.DTO.salida.TopicoSalidaDTO;
import com.alura.utilities.Agregaciones;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	private Agregaciones agregaciones;
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public TopicoController(
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
	public ResponseEntity<TopicoSalidaDTO> agregarTopico(@RequestBody @Valid TopicoDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		TopicoSalidaDTO nuevoTopico = agregaciones.agregarTopico(dto);
		URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
		return ResponseEntity.created(url).body(nuevoTopico);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<TopicoSalidaDTO>> obtenerTodosLosTopicos(@PageableDefault(size = 10) Pageable paginacion) {
		Page<TopicoSalidaDTO> lista = fromModelToDTO.obtenerTodosLosTopicos(paginacion);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoSalidaDTO> obtenerTopico(@PathVariable Integer id) {
		TopicoSalidaDTO topico = fromModelToDTO.obtenerTopico(id);
		return ResponseEntity.ok(topico);
	}
	
	@PutMapping
	public ResponseEntity<TopicoSalidaDTO> modificarTopico(@RequestBody @Valid TopicoModificacionesDTO dto) {
		TopicoSalidaDTO topicoModificado = modificaciones.modificarTopico(dto);
		return ResponseEntity.ok(topicoModificado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eleminarTopico(@PathVariable Integer id) {
		eliminaciones.eliminarTopico(id);
		return ResponseEntity.noContent().build();
	}
}
