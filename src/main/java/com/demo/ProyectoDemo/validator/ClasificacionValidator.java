package com.demo.ProyectoDemo.validator;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClasificacionValidator implements ConstraintValidator<Clasificacion, String>{

	List<String> clasificacionAceptada = Arrays.asList("TIPO A", "TIPO B");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {		
		return clasificacionAceptada.contains(value);
	}
	
	
}
