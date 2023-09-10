package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.TopicoDTO;
import com.alura.DTO.modificaciones.TopicoModificacionesDTO;
import com.alura.DTO.salida.TopicoSalidaDTO;
import com.alura.modelo.Topico;
import com.alura.repositories.TopicoRepository;
import com.alura.utilities.FromDTOtoModel;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	private TopicoRepository topicoRepository;
	private FromDTOtoModel fromDTOtoModel;
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	
	@Autowired
	public TopicoController(
		TopicoRepository topicoRepository, 
		FromDTOtoModel fromDTOtoModel, 
		FromModelToDTO fromModelToDTO,
		Modificaciones modificaciones
	) {
		this.fromDTOtoModel = fromDTOtoModel;		
		this.fromModelToDTO = fromModelToDTO;
		this.topicoRepository = topicoRepository;
		this.modificaciones = modificaciones;
	}
	@PostMapping
	public void agregarTopico(@RequestBody @Valid TopicoDTO dto) {
		Topico topico = fromDTOtoModel.createTopico(dto);
		topicoRepository.save(topico);
	}
	
	@GetMapping
	public Page<TopicoSalidaDTO> obtenerTodosLosTopicos(@PageableDefault(size = 10) Pageable paginacion) {
		Page<TopicoSalidaDTO> lista = fromModelToDTO.obtenerTodosLosTopicos(paginacion);
		return lista;
	}
	
	@PutMapping
	public void modificarTopico(@RequestBody @Valid TopicoModificacionesDTO dto) {
		modificaciones.modificarTopico(dto);
	}
}
