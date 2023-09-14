package com.alura.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.DTO.CursoDTO;
import com.alura.DTO.RespuestaDTO;
import com.alura.DTO.modificaciones.CursoModificacionesDTO;
import com.alura.DTO.modificaciones.RespuestaModificacionesDTO;
import com.alura.DTO.modificaciones.TopicoModificacionesDTO;
import com.alura.DTO.modificaciones.UsuarioMoficacionesDTO;
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

import jakarta.transaction.Transactional;

@Component
public class Modificaciones {
	
	private UsuarioRepository usuarioRepository;
	private CursoRepository cursoRepository;
	private TopicoRepository topicoRepository;
	private RespuestaRepository respuestaRepository;
	
	@Autowired
	public Modificaciones(
		UsuarioRepository usuarioRepository, 
		CursoRepository cursoRepository, 
		TopicoRepository topicoRepository, 
		RespuestaRepository respuestaRepository
	) {
		this.usuarioRepository = usuarioRepository;
		this.cursoRepository =cursoRepository;
		this.topicoRepository = topicoRepository;
		this.respuestaRepository = respuestaRepository;
	}
	
	@Transactional
	public UsuarioSalidaDTO modificarUsuario(UsuarioMoficacionesDTO dto) {
		Usuario usuario = usuarioRepository.findById(dto.getId()).get();
		if(dto.getEmail() != null && dto.getEmail().trim().length() > 0) {
			usuario.setEmail(dto.getEmail());
		}
		if(dto.getPassword() != null && dto.getPassword().trim().length() > 0) {
			usuario.setContrasena(dto.getPassword());
		}
		return new UsuarioSalidaDTO(usuario);
	}
	
	@Transactional
	public CursoDTO modificarCurso(CursoModificacionesDTO dto) {
		Curso curso = cursoRepository.findById(dto.getId()).get();
		if(dto.getNombre() != null && dto.getNombre().trim().length() > 0) {
			curso.setNombre(dto.getNombre());
		}
		if(dto.getCategoria() != null && dto.getCategoria().trim().length() > 0) {
			curso.setCategoria(dto.getCategoria());
		}
		return new CursoDTO(curso);
	}
	
	@Transactional
	public TopicoSalidaDTO modificarTopico(TopicoModificacionesDTO dto) {
		Topico topico = topicoRepository.findById(dto.getId()).get();
		if(dto.getTitulo() != null && dto.getTitulo().trim().length() > 0) {
			topico.setTitulo(dto.getTitulo());
		}
		if(dto.getMensaje() != null && dto.getMensaje().trim().length() > 0) {
			topico.setMensaje(dto.getMensaje());
		}
		if(dto.getCurso() != null && dto.getCurso() >= 0) {
			Curso curso = cursoRepository.findById(dto.getCurso()).get();
			topico.setCurso(curso);
		}
		if(dto.getStatus() != null) {
			topico.setStatus(dto.getStatus());
		}
		return new TopicoSalidaDTO(topico);
	}
	
	@Transactional
	public RespuestaDTO modificarRespuesta(RespuestaModificacionesDTO dto) {
		Respuesta respuesta = respuestaRepository.findById(dto.getId()).get();
		if(dto.getMensaje() != null && dto.getMensaje().trim().length() > 0) {
			respuesta.setMensaje(dto.getMensaje());
		}
		if(dto.getSolucion() != null && dto.getSolucion() == true) {
			Topico topico = topicoRepository.findById(respuesta.getTopico().getId()).get();
			topico.setStatus(StatusTopico.SOLUCIONADO);
			respuesta.setSolucion(dto.getSolucion());
		}
		return new RespuestaDTO(respuesta);
	}
}
