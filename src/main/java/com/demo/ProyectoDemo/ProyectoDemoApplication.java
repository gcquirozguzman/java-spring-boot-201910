package com.demo.ProyectoDemo;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.demo.ProyectoDemo.entity.LibroEntity;
import com.demo.ProyectoDemo.repository.LibroRepository;

@SpringBootApplication
public class ProyectoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoDemoApplication.class, args);
	}
	
	@Bean
	@Profile("ambiente_desarrollo")
	CommandLineRunner iniciarBaseDatos(LibroRepository libroRepository) {
		return args -> {
			libroRepository.save(new LibroEntity("Autor 1", "Libro 2011", "TIPO A", new BigDecimal("10")));
			libroRepository.save(new LibroEntity("Autor 2", "Libro 2012", "TIPO B", new BigDecimal("20")));
			libroRepository.save(new LibroEntity("Autor 3", "Libro 2013", "TIPO A", new BigDecimal("30")));
			libroRepository.save(new LibroEntity("Autor 4", "Libro 2014", "TIPO B", new BigDecimal("40")));
			libroRepository.save(new LibroEntity("Autor 5", "Libro 2015", "TIPO A", new BigDecimal("50")));
			libroRepository.save(new LibroEntity("Autor 6", "Libro 2016", "TIPO B", new BigDecimal("60")));
		};
	}

}
