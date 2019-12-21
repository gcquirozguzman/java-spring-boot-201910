package com.demo.ProyectoDemo.restController;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ProyectoDemo.entity.LibroEntity;
import com.demo.ProyectoDemo.exception.LibroNotFoundException;
import com.demo.ProyectoDemo.exception.LibroUnSupportedFieldPatchException;
import com.demo.ProyectoDemo.repository.LibroRepository;

@RestController
@RequestMapping("libro/")
public class LibroRestController {

	@Autowired
	LibroRepository libroRepository;
	
	@GetMapping("listarTodos")
	List<LibroEntity> listarTodos(){
		return libroRepository.findAll();
	}
	
	@PostMapping("crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	LibroEntity crear(@Valid @RequestBody LibroEntity libroEntity) {
		return libroRepository.save(libroEntity);
	}
	
	@GetMapping("buscar/{id}")
	LibroEntity buscarById(@PathVariable @Min(1) Long id){
		return libroRepository.findById(id).orElseThrow(() -> new LibroNotFoundException(id));
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	void eliminar(@PathVariable Long id) {
		libroRepository.deleteById(id);
	}
	
	@PutMapping("actualizarCrear/{id}")
	LibroEntity grabarActualizar(@RequestBody LibroEntity libroEntity, @PathVariable Long id) {
		
		return libroRepository.findById(id)
				.map(
						x -> {
							x.setAutor(libroEntity.getAutor());
							x.setClasificacion(libroEntity.getClasificacion());
							x.setTitulo(libroEntity.getTitulo());
							x.setPrecio(libroEntity.getPrecio());
							return libroRepository.save(x);
						}
				)
				.orElseGet(
						() -> {
							return libroRepository.save(libroEntity);
						}
				);		
	}
	
	@PatchMapping("actualizar/{id}")
	LibroEntity actualizarPatch(@RequestBody Map<String, String> parametro, @PathVariable Long id) {
		return libroRepository.findById(id)
				.map(
						x -> {
							String autor = parametro.get("autor");
							if(!StringUtils.isEmpty(autor)) {
								x.setAutor(autor);
								return libroRepository.save(x);
							} else {
								throw new LibroUnSupportedFieldPatchException(parametro.keySet());
							}
							
						}
				)
				.orElseGet(
						() -> {
							throw new LibroNotFoundException(id);
						}
				);
	}
	
}