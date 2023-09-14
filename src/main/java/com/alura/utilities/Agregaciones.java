package com.alura.utilities;

import org.springframework.stereotype.Component;

import com.alura.DTO.CursoDTO;
import com.alura.DTO.RespuestaDTO;
import com.alura.DTO.TopicoDTO;
import com.alura.DTO.UsuarioDTO;
import com.alura.DTO.salida.TopicoSalidaDTO;
import com.alura.DTO.salida.UsuarioSalidaDTO;
import com.alura.modelo.Curso;
import com.alura.modelo.Respuesta;
import com.alura.modelo.StatusTopico;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import com.alura.repositories.CursoRepository;
import com.alura.repositories.RespuestaRepository;
import com.alura.repositories.TopicoRepository;
import com.alura.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@Component
public class Agregaciones {

	private UsuarioRepository usuarioRepository;
	private CursoRepository cursoRepository;
	private TopicoRepository topicoRepository;
	private FromDTOtoModel fromDTOtoModel; 
	private RespuestaRepository respuestaRepository;
	
	public Agregaciones(
		UsuarioRepository usuarioRepository,
		CursoRepository cursoRepository,
		TopicoRepository topicoRepository,
		FromDTOtoModel fromDTOtoModel,
		RespuestaRepository respuestaRepository
	) {
		this.usuarioRepository=usuarioRepository;
		this.cursoRepository=cursoRepository;
		this.topicoRepository=topicoRepository;
		this.fromDTOtoModel = fromDTOtoModel;
		this.respuestaRepository = respuestaRepository;
	}
	
	public UsuarioSalidaDTO agregarUsuario(@Valid UsuarioDTO dto) {
		Usuario usuario = fromDTOtoModel.createUsuario(dto);
		usuario = usuarioRepository.save(usuario);
		return new UsuarioSalidaDTO(usuario);
	}
	
	public CursoDTO agregarCurso(@Valid CursoDTO dto) {
		Curso nuevoCurso = fromDTOtoModel.createCurso(dto);
		nuevoCurso = cursoRepository.save(nuevoCurso);
		return new CursoDTO(nuevoCurso);
	}
	
	public TopicoSalidaDTO agregarTopico(@Valid TopicoDTO dto) {
		Topico topico = fromDTOtoModel.createTopico(dto);
		topico = topicoRepository.save(topico);
		return new TopicoSalidaDTO(topico);
	}
	
	public RespuestaDTO agregarRespuesta(@Valid RespuestaDTO dto) {
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
		//topico.agregarRespuesta(respuesta);
		// UPDATE the topico with the new answer
		topicoRepository.save(topico);
		respuesta.setTopico(topico);
		respuesta = respuestaRepository.save(respuesta);
		return new RespuestaDTO(respuesta);
	}
}
