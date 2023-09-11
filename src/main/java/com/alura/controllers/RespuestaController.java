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

import com.alura.DTO.RespuestaDTO;
import com.alura.DTO.modificaciones.RespuestaModificacionesDTO;
import com.alura.modelo.Respuesta;
import com.alura.modelo.StatusTopico;
import com.alura.modelo.Topico;
import com.alura.repositories.TopicoRepository;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromDTOtoModel;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

	private FromDTOtoModel fromDTOtoModel;
	private FromModelToDTO fromModelToDTO;
	private TopicoRepository topicoRepository;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public RespuestaController(
		FromDTOtoModel fromDTOtoModel, 
		TopicoRepository topicoRepository, 
		FromModelToDTO fromModelToDTO,
		Modificaciones modificaciones,
		Eliminaciones eliminaciones
	) {
		this.fromDTOtoModel = fromDTOtoModel;
		this.fromModelToDTO = fromModelToDTO;
		this.topicoRepository = topicoRepository;
		this.modificaciones = modificaciones;
		this.eliminaciones = eliminaciones;
	}
	
	@PostMapping
	public void agregarRespuesta(@RequestBody @Valid RespuestaDTO dto) {
		Respuesta respuesta = fromDTOtoModel.createRespuesta(dto);
		Topico topico = topicoRepository.findById(dto.getTopico()).get();
		//If the topico is closed no more respuestas can be added
		if(topico.getStatus() == StatusTopico.CERRADO) {
			throw new RuntimeException("Topico cerrado");
		}
		// Change the status of the topic if it's solved
		if(respuesta.getSolucion()==true) {
			topico.setStatus(StatusTopico.SOLUCIONADO);
		}
		if(topico.getRespuestas().size()==0 && topico.getStatus() == StatusTopico.NO_RESPONDIDO) {
			topico.setStatus(StatusTopico.NO_SOLUCIONADO);
		}
		topico.agregarRespuesta(respuesta);
		// UPDATE the topico with the new answer
		topicoRepository.save(topico);
	}
	
	@GetMapping
	public Page<RespuestaDTO> obtenerTodasLasRespuestas(@PageableDefault(size = 10) Pageable paginacion){
		Page<RespuestaDTO> lista = fromModelToDTO.obtenerTodasLasRespuestas(paginacion);
		return lista;
	}
	
	@GetMapping("/topico/{id}")
	public Page<RespuestaDTO> obtenerRespuestasPorTopico(
		@PageableDefault(size = 10) Pageable paginacion, 
		@PathVariable Integer id
	){
		Page<RespuestaDTO> lista = fromModelToDTO.obtenerRespuestasPorTopico(paginacion, id);
		return lista;
	}
	
	@GetMapping("/{id}")
	public RespuestaDTO obtenerRespuesta(@PathVariable Integer id){
		return fromModelToDTO.obtenerRespuesta(id);
	}
	
	@PutMapping
	public void modificarRespuesta(@RequestBody @Valid RespuestaModificacionesDTO dto) {
		modificaciones.modificarRespuesta(dto);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarRespuesta(@PathVariable Integer id) {
		eliminaciones.eliminarRespuesta(id);
	}
}
