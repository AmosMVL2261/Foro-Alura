package com.alura.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.DTO.CursoDTO;
import com.alura.DTO.RespuestaDTO;
import com.alura.DTO.TopicoDTO;
import com.alura.DTO.UsuarioDTO;
import com.alura.modelo.Curso;
import com.alura.modelo.Respuesta;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import com.alura.repositories.CursoRepository;
import com.alura.repositories.UsuarioRepository;

@Component
public class FromDTOtoModel {
	
	private UsuarioRepository usuarioRepository;
	private CursoRepository cursoRepository;
	
	@Autowired
	public FromDTOtoModel(UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.cursoRepository = cursoRepository;
	}
	
	public Curso createCurso(CursoDTO dto) {
		Curso c = new Curso();
		c.setNombre(dto.getNombre());
		c.setCategoria(dto.getCategoria());
		return c;
	}
	
	public Usuario createUsuario(UsuarioDTO dto) {
		Usuario u = new Usuario();
		u.setNombre(dto.getNombre());
		u.setEmail(dto.getEmail());
		u.setContrasena(dto.getPassword());
		return u;
	}
	
	public Topico createTopico(TopicoDTO dto) {
		System.out.println(dto);
		Topico topicoNuevo = new Topico();
		
		topicoNuevo.setTitulo(dto.getTitulo());
		
		topicoNuevo.setMensaje(dto.getMensaje());
		
		Usuario usuario = usuarioRepository.findById(dto.getAutor()).get();
		topicoNuevo.setAutor(usuario);
		
		Curso curso = cursoRepository.findById(dto.getCurso()).get();
		topicoNuevo.setCurso(curso);
		
		return topicoNuevo;
	}
	
	
	public Respuesta createRespuesta(RespuestaDTO dto) {
		
		Respuesta respuestaNueva = new Respuesta();
		
		respuestaNueva.setMensaje(dto.getMensaje());
	
		Usuario usuario = usuarioRepository.findById(dto.getAutor()).get();
		respuestaNueva.setAutor(usuario);
		//If the respuesta solve the topico, then solucion = true
		if(dto.getSolucion()==true) {
			respuestaNueva.setSolucion(true);
		}
		
		return respuestaNueva;
	}
}
