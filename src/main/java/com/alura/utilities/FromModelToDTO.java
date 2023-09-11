package com.alura.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.alura.DTO.CursoDTO;
import com.alura.DTO.RespuestaDTO;
import com.alura.DTO.salida.TopicoSalidaDTO;
import com.alura.DTO.salida.UsuarioSalidaDTO;
import com.alura.modelo.Curso;
import com.alura.modelo.Respuesta;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import com.alura.repositories.CursoRepository;
import com.alura.repositories.RespuestaRepository;
import com.alura.repositories.TopicoRepository;
import com.alura.repositories.UsuarioRepository;

@Component
public class FromModelToDTO {
	
	private CursoRepository cursoRespository;
	private UsuarioRepository usuarioRepository;
	private TopicoRepository topicoRepository;
	private RespuestaRepository respuestaRepository;
	
	@Autowired
	public FromModelToDTO(
		CursoRepository cursoRespository, 
		UsuarioRepository usuarioRepository,
		TopicoRepository topicoRepository,
		RespuestaRepository respuestaRepository
	) {
		this.cursoRespository = cursoRespository;
		this.usuarioRepository = usuarioRepository;
		this.topicoRepository = topicoRepository;
		this.respuestaRepository = respuestaRepository;
	}
	
	public Page<CursoDTO> obtenerTodosLosCursos(Pageable paginacion) {
		Page<CursoDTO> listaNueva = cursoRespository.findAll(paginacion).map(elemento -> {
			return new CursoDTO(
				elemento.getId(),
				elemento.getNombre(),
				elemento.getCategoria()
			);
			});
		return listaNueva;
	}
	
	public Page<UsuarioSalidaDTO> obtenerTodosLosUsuarios(Pageable paginacion) {
		Page<UsuarioSalidaDTO> listaNueva = usuarioRepository.findAll(paginacion).map(elemento -> {
			return new UsuarioSalidaDTO(
				elemento.getId(),
				elemento.getNombre(),
				elemento.getEmail()
			);
		});
		return listaNueva;
	}
	
	public Page<TopicoSalidaDTO> obtenerTodosLosTopicos(Pageable paginacion) {
		Page<TopicoSalidaDTO> listaNueva = topicoRepository.findAll(paginacion).map(elemento -> {
			return new TopicoSalidaDTO(
				elemento.getId(),
				elemento.getTitulo(),
				elemento.getMensaje(),
				elemento.getfechaCreacion(),
				elemento.getStatus(),
				elemento.getAutor().getId(),
				elemento.getCurso().getId(),
				elemento.getRespuestas().size()
			);
		});
		return listaNueva;
	}
	
	public Page<RespuestaDTO> obtenerTodasLasRespuestas(Pageable paginacion) {
		Page<RespuestaDTO> listaNueva = respuestaRepository.findAll(paginacion).map(elemento -> {
			return new RespuestaDTO(
				elemento.getId(),
				elemento.getMensaje(),
				elemento.getTopico().getId(),
				elemento.getfechaCreacion(),
				elemento.getAutor().getId(),
				elemento.getSolucion()
			);
		});
		return listaNueva;
	}
	
	public UsuarioSalidaDTO obtenerUsuario(Integer id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		return new UsuarioSalidaDTO(
			usuario.getId(),
			usuario.getNombre(),
			usuario.getEmail()
		);
	}
	
	public CursoDTO obtenerCurso(Integer id) {
		Curso curso = cursoRespository.findById(id).get();
		return new CursoDTO(
			curso.getId(),
			curso.getNombre(),
			curso.getCategoria()
		);
	}
	
	public TopicoSalidaDTO obtenerTopico(Integer id) {
		Topico topico = topicoRepository.findById(id).get();
		return new TopicoSalidaDTO(
			topico.getId(),
			topico.getTitulo(),
			topico.getMensaje(),
			topico.getfechaCreacion(),
			topico.getStatus(),
			topico.getAutor().getId(),
			topico.getCurso().getId(),
			topico.getRespuestas().size()
		);
	}
	
	public RespuestaDTO obtenerRespuesta(Integer id) {
		Respuesta respuesta = respuestaRepository.findById(id).get();
		return new RespuestaDTO(
			respuesta.getId(),
			respuesta.getMensaje(),
			respuesta.getTopico().getId(),
			respuesta.getfechaCreacion(),
			respuesta.getAutor().getId(),
			respuesta.getSolucion()
		);
	}

	
	public Page<RespuestaDTO> obtenerRespuestasPorTopico(Pageable paginacion, Integer topicoId) {
		Page<RespuestaDTO> listaNueva = respuestaRepository.findAllByTopicoId(topicoId, paginacion).map(elemento -> {
			return new RespuestaDTO(
				elemento.getId(),
				elemento.getMensaje(),
				elemento.getTopico().getId(),
				elemento.getfechaCreacion(),
				elemento.getAutor().getId(),
				elemento.getSolucion()
			);
		});
		return listaNueva;
	}
}
