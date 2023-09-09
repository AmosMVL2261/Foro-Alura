package com.alura.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "respuestas")
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mensaje;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topicoId", nullable = false)
	private Topico topico;
	private LocalDateTime fechaDeCreacion = LocalDateTime.now();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autorId", nullable=false)
	private Usuario autor;
	private Boolean solucion = false;
	
	public Respuesta() {
		
	}

	public Respuesta(Integer id, String mensaje, Topico topico, LocalDateTime fechaDeCreacion, Usuario autor,
			Boolean solucion) {
		this.id = id;
		this.mensaje = mensaje;
		this.topico = topico;
		this.fechaDeCreacion = fechaDeCreacion;
		this.autor = autor;
		this.solucion = solucion;
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
		Respuesta other = (Respuesta) obj;
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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public LocalDateTime getfechaCreacion() {
		return fechaDeCreacion;
	}

	public void setfechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaDeCreacion = fechaCreacion;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}

	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", mensaje=" + mensaje + ", topico=" + topico + ", fechaDeCreacion="
				+ fechaDeCreacion + ", autor=" + autor + ", solucion=" + solucion + "]";
	}

}
