package com.alura.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "topicos")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaDeCreacion = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autorId", nullable = false)
	private Usuario autor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cursoId", nullable = false)
	private Curso curso;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico() {
		
	}
	
	public Topico(String titulo, String mensaje, Curso curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.curso = curso;
	}

	public Topico(Integer id, String titulo, String mensaje, LocalDateTime fechaDeCreacion, StatusTopico status,
			Usuario autor, Curso curso, List<Respuesta> respuestas) {
		this.id = id;
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.fechaDeCreacion = fechaDeCreacion;
		this.status = status;
		this.autor = autor;
		this.curso = curso;
		this.respuestas = respuestas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getfechaCreacion() {
		return fechaDeCreacion;
	}

	public void setfechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaDeCreacion = fechaCreacion;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public String toString() {
		return "Topico [id=" + id + ", titulo=" + titulo + ", mensaje=" + mensaje + ", fechaDeCreacion="
				+ fechaDeCreacion + ", status=" + status + ", autor=" + autor + ", curso=" + curso + ", respuestas="
				+ respuestas + "]";
	}
	
	public void agregarRespuesta(Respuesta r) {
		// Add the new respuesta
		this.respuestas.add(r);
		// Link this topico with the respuesta
		r.setTopico(this);
	}

}
