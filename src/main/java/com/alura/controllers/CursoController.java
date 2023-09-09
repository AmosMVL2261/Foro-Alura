package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.CursoDTO;
import com.alura.modelo.Curso;
import com.alura.repositories.CursoRepository;
import com.alura.utilities.FromDTOtoModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	private CursoRepository cursoRepository; 
	private FromDTOtoModel fromTo;
	
	@Autowired
	public CursoController(CursoRepository cursoRepository, FromDTOtoModel fromTo) {
		this.cursoRepository = cursoRepository;
		this.fromTo = fromTo;
	}
	
	@PostMapping
	public void agregarCurso(@RequestBody @Valid CursoDTO dto) {
		Curso nuevoCurso = fromTo.createCurso(dto);
		cursoRepository.save(nuevoCurso);
	}
	
}
