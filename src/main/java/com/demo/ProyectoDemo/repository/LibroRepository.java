package com.demo.ProyectoDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.ProyectoDemo.entity.LibroEntity;

public interface LibroRepository extends JpaRepository<LibroEntity, Long>{
	List<LibroEntity> findByAutorOrderByTituloAsc(String autor);
}
