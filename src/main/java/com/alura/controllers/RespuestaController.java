package com.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.DTO.RespuestaDTO;
import com.alura.modelo.Respuesta;
import com.alura.modelo.StatusTopico;
import com.alura.modelo.Topico;
import com.alura.repositories.TopicoRepository;
import com.alura.utilities.FromDTOtoModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

	private FromDTOtoModel from;
	private TopicoRepository topicoRepository;
	
	@Autowired
	public RespuestaController(FromDTOtoModel from, TopicoRepository topicoRepository) {
		this.from = from;
		this.topicoRepository = topicoRepository;
	}
	
	@PostMapping
	public void agregarRespuesta(@RequestBody @Valid RespuestaDTO dto) {
		Respuesta respuesta = from.createRespuesta(dto);
		Topico topico = topicoRepository.findById(dto.getTopico()).get();
		// Change the status of the topic if it's solved
		if(respuesta.getSolucion()==true) {
			topico.setStatus(StatusTopico.SOLUCIONADO);
		}
		topico.agregarRespuesta(respuesta);
		// UPDATE the topico with the new answer
		topicoRepository.save(topico);
	}
}
