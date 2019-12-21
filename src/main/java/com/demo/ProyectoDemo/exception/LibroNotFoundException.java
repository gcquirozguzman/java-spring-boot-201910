package com.demo.ProyectoDemo.exception;

@SuppressWarnings("serial")
public class LibroNotFoundException extends RuntimeException{
	
	public LibroNotFoundException(Long id) {
		super("Libro no encontrado con id : "+id);
	}
	
}