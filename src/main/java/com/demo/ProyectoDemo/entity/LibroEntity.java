package com.demo.ProyectoDemo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class LibroEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "Ingrese un autor")
	private String autor;
	
	@NotEmpty(message = "Ingrese un titulo")
	private String titulo;
	
	@NotEmpty(message = "Ingrese una clasificacion")
	private String clasificacion;
	
	@DecimalMin("1.00")
	@NotNull(message = "Ingrese un precio")
	private BigDecimal precio;
	
	public LibroEntity() {
	}

	public LibroEntity(Long id, String autor, String titulo, String clasificacion, BigDecimal precio) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.clasificacion = clasificacion;
		this.precio = precio;
	}

	public LibroEntity(String autor, String titulo, String clasificacion, BigDecimal precio) {
		this.autor = autor;
		this.titulo = titulo;
		this.clasificacion = clasificacion;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	
}
