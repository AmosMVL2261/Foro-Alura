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

import com.alura.DTO.CursoDTO;
import com.alura.modelo.Curso;
import com.alura.repositories.CursoRepository;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromDTOtoModel;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	private CursoRepository cursoRepository; 
	private FromDTOtoModel fromDTOtoModel;
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public CursoController(
		CursoRepository cursoRepository, 
		FromDTOtoModel fromDTOtoModel, 
		FromModelToDTO fromModelToDTO,
		Modificaciones modificaciones,
		Eliminaciones eliminaciones
	) {
		this.cursoRepository = cursoRepository;
		this.fromDTOtoModel = fromDTOtoModel;
		this.fromModelToDTO = fromModelToDTO;
		this.modificaciones = modificaciones;
		this.eliminaciones = eliminaciones;
	}
	
	@PostMapping
	public void agregarCurso(@RequestBody @Valid CursoDTO dto) {
		Curso nuevoCurso = fromDTOtoModel.createCurso(dto);
		cursoRepository.save(nuevoCurso);
	}
	
	@GetMapping
	public Page<CursoDTO> obtenerTodosLosCursos(@PageableDefault(size = 10) Pageable paginacion) {
		Page<CursoDTO> lista = fromModelToDTO.obtenerTodosLosCursos(paginacion);
		return lista;
	}
	
	@GetMapping("/{id}")
	public CursoDTO obtenerCurso(@PathVariable Integer id) {
		return fromModelToDTO.obtenerCurso(id);
	}
	
	@PutMapping
	public void modificarCurso(@RequestBody @Valid CursoDTO dto) {
		modificaciones.modificarCurso(dto);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarCurso(@PathVariable Integer id) {
		eliminaciones.eliminarCurso(id);
	}
	
}
