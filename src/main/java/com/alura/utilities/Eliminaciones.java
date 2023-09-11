package com.alura.utilities;

import org.springframework.stereotype.Component;

import com.alura.modelo.Curso;
import com.alura.modelo.Respuesta;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import com.alura.repositories.CursoRepository;
import com.alura.repositories.RespuestaRepository;
import com.alura.repositories.TopicoRepository;
import com.alura.repositories.UsuarioRepository;

@Component
public class Eliminaciones {
	
	private UsuarioRepository usuarioRepository;
	private CursoRepository cursoRepository;
	private TopicoRepository topicoRepository;
	private RespuestaRepository respuestaRepository;
	
	public Eliminaciones(
			UsuarioRepository usuarioRepository,
			CursoRepository cursoRepository,
			TopicoRepository topicoRepository,
			RespuestaRepository respuestaRepository		
	) {
		this.usuarioRepository = usuarioRepository;
		this.cursoRepository = cursoRepository;
		this.topicoRepository = topicoRepository;
		this.respuestaRepository = respuestaRepository;
	}

	public void eliminarUsuario(Integer id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}
	
	public void eliminarCurso(Integer id) {
		Curso curso = cursoRepository.findById(id).get();
		cursoRepository.delete(curso);
	}
	
	public void eliminarTopico(Integer id) {
		Topico topico = topicoRepository.findById(id).get();
		topicoRepository.delete(topico);
	}
	
	public void eliminarRespuesta(Integer id) {
		Respuesta respuesta = respuestaRepository.findById(id).get();
		respuestaRepository.delete(respuesta);
	}
	
}
