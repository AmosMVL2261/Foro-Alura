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

import com.alura.DTO.RespuestaDTO;
import com.alura.DTO.modificaciones.RespuestaModificacionesDTO;
import com.alura.utilities.Agregaciones;
import com.alura.utilities.Eliminaciones;
import com.alura.utilities.FromModelToDTO;
import com.alura.utilities.Modificaciones;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

	private Agregaciones agregaciones;
	private FromModelToDTO fromModelToDTO;
	private Modificaciones modificaciones;
	private Eliminaciones eliminaciones;
	
	@Autowired
	public RespuestaController(
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
	public ResponseEntity<RespuestaDTO> agregarRespuesta(@RequestBody @Valid RespuestaDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		RespuestaDTO respuestaNueva = agregaciones.agregarRespuesta(dto);
		URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuestaNueva.getId()).toUri();
		return ResponseEntity.created(url).body(respuestaNueva);
	}
	
	@GetMapping
	public ResponseEntity<Page<RespuestaDTO>> obtenerTodasLasRespuestas(@PageableDefault(size = 10) Pageable paginacion){
		Page<RespuestaDTO> lista = fromModelToDTO.obtenerTodasLasRespuestas(paginacion);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/topico/{id}")
	public ResponseEntity<Page<RespuestaDTO>> obtenerRespuestasPorTopico(
		@PageableDefault(size = 10) Pageable paginacion, 
		@PathVariable Integer id
	){
		Page<RespuestaDTO> lista = fromModelToDTO.obtenerRespuestasPorTopico(paginacion, id);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RespuestaDTO> obtenerRespuesta(@PathVariable Integer id){
		RespuestaDTO respuesta = fromModelToDTO.obtenerRespuesta(id);
		return ResponseEntity.ok(respuesta);
	}
	
	@PutMapping
	public ResponseEntity<RespuestaDTO> modificarRespuesta(@RequestBody @Valid RespuestaModificacionesDTO dto) {
		RespuestaDTO respuestaModificada = modificaciones.modificarRespuesta(dto);
		return ResponseEntity.ok(respuestaModificada);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarRespuesta(@PathVariable Integer id) {
		eliminaciones.eliminarRespuesta(id);
		return ResponseEntity.noContent().build();
	}
}
