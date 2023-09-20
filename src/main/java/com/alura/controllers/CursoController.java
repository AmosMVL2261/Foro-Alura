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

import com.alura.DTO.CursoDTO;
import com.alura.DTO.modificaciones.CursoModificacionesDTO;
import com.alura.utilities.Agregaciones;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
	
	private Agregaciones agregaciones; 
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public CursoController(
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
	public ResponseEntity<CursoDTO> agregarCurso(@RequestBody @Valid CursoDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		CursoDTO nuevoCurso = agregaciones.agregarCurso(dto);
		URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(nuevoCurso.getId()).toUri();
		return ResponseEntity.created(url).body(nuevoCurso);
	}
	
	@GetMapping
	public ResponseEntity<Page<CursoDTO>> obtenerTodosLosCursos(@PageableDefault(size = 10) Pageable paginacion) {
		Page<CursoDTO> lista = fromModelToDTO.obtenerTodosLosCursos(paginacion);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoDTO> obtenerCurso(@PathVariable Integer id) {
		CursoDTO curso = fromModelToDTO.obtenerCurso(id);
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping
	public ResponseEntity<CursoDTO> modificarCurso(@RequestBody @Valid CursoModificacionesDTO dto) {
		CursoDTO cursoModificado = modificaciones.modificarCurso(dto);
		return ResponseEntity.ok(cursoModificado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
		eliminaciones.eliminarCurso(id);
		return ResponseEntity.noContent().build();
	}
}
