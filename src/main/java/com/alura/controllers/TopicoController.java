package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.TopicoDTO;
import com.alura.modelo.Topico;
import com.alura.repositories.TopicoRepository;
import com.alura.utilities.FromDTOtoModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	private FromDTOtoModel from;
	private TopicoRepository topicoRepository;
	
	@Autowired
	public TopicoController(FromDTOtoModel from, TopicoRepository topicoRepository) {
		this.from = from;
		this.topicoRepository = topicoRepository;
	}
	@PostMapping
	public void agregarTopico(@RequestBody @Valid TopicoDTO dto) {
		Topico topico = from.createTopico(dto);
		topicoRepository.save(topico);
	}
}
