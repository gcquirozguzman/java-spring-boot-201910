package com.demo.ProyectoDemo.exception;

import java.util.Set;

@SuppressWarnings("serial")
public class LibroUnSupportedFieldPatchException extends RuntimeException{

	public LibroUnSupportedFieldPatchException(Set<String> keys) {
		super("Campo "+keys.toString()+" no esta permitido");
	}
	
}