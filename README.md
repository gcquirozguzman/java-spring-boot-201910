# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 6: Implementación de Servicios._

```
📢 Creamos el siguiente paquete.

	com.demo.ProyectoDemo.restController

📢  En el paquete com.demo.ProyectoDemo.restController creamos la siguiente clase:
	
	LibroRestController

📢  Usaremos las siguientes anotaciones para poder diferentes tipos de servicios.
	
	@RestController - Permite que la clase se publique como un Spring REST Service.
	@RequestMapping - Permite relacionar un método con una petición http.
	@ResponseStatus - Permite mandar un estado de respuesta. Por ejemplo CREATED.
	
	@GetMapping = RequestMethod.GET
	@PostMapping = RequestMethod.POST
	@DeleteMapping = RequestMethod.DELETE
	@PutMapping = RequestMethod.PUT
	@PatchMapping = RequestMethod.PATCH
	
📢  Primero agregamos la anotación @RestController a la clase. De la misma manera, adicionamos @RequestMapping, para indicar que todos nuestros servicios parten de la ruta "libro/". Ejemplo "libro/grabar", "libro/actualizar" o "libro/buscar"

📢  Llamaremos al repositorio LibroRepository y agregaremos @Autowired para que se realice la inyección automática.
📢  A continuación crearemos el método listarTodos.
	
	listarTodos - @GetMapping("listarTodos")
	libroRepository.findAll()

📢  A continuación crearemos el método crear. Llamamos al repositorio libroRepository y a su método save, enviándole como parámetro a LibroEntity. Podemos darnos cuenta que nosotros no hemos creado este método, pero como se comentó, al heredar JpaRepository traemos a todos sus métodos.

	crear - @PostMapping("crear")
	libroRepository.save(libroEntity)

📢  A continuación crearemos el método buscarById. Llamamos al método findById y le enviamos como parámetro el ID. Para solucionar la advertencia que nos hace Java, debemos de agregar la excepción en caso no se encuentre un registro en el ID solicitado. 

	buscarById - @GetMapping("buscar/{id}")
	libroRepository.findById(id)
	.orElseThrow( 								// 1) En caso suceda un error.
		() -> 								// 2) No recuperamos ningún parámetro.
		new LibroNotFoundException(id)					// 3) Enviamos la excepción LibroNotFoundException.
	);
	
📢  A continuación crearemos el método eliminar. Llamamos al método deleteById y le enviamos como parámetro el ID. 

	eliminar - @DeleteMapping("eliminar/{id}")
	libroRepository.deleteById(id)

📢  A continuación crearemos el método grabarActualizar. Inicialmente llamamos al método findById y le enviamos como parámetro el ID. Si el resultado retorna un valor, actualiza. Caso contrario, crea un registro nuevo.

	grabarActualizar - @PutMapping("actualizarCrear/{id}")
	libroRepository.findById(id)						// 1) Se busca un registro por ID.
	.map(									// 2) Si encuentra registro ingresamos a esta opción.
		x -> {								// 3) X contiene el resultado de la búsqueda.
			x.setAutor(libroEntity.getAutor());			// 4) Actualizamos autor.
			x.setClasificacion(libroEntity.getClasificacion());	// 5) Actualizamos clasificación.
			x.setTitulo(libroEntity.getTitulo());			// 6) Actualizamos título.
			x.setPrecio(libroEntity.getPrecio());			// 7) Actualizamos precio.
			return libroRepository.save(x);				// 8) Grabamos cambios.
		}
	)
	.orElseGet(								// 9) En caso no se encuentre registro.
		() -> {								// 10) No recuperamos ningún parámetro.
			return libroRepository.save(libroEntity);		// 11) Grabamos cambios.
		}
	);	

📢  A continuación crearemos el método actualizarPatch.	Inicialmente llamamos al método findById y le enviamos como parámetro el ID. Para usar este método, consideremos que queremos servicio que solo modifique sus atributos a petición.
	
	{
		"autor": "Mario Vargas Llosa"					// Trama de envio. Solo quiero que se modifique autor.
	}
	
	actualizarPatch - @PatchMapping("actualizar/{id}")
	libroRepository.findById(id)						// 1) Se busca un registro por ID.
	.map(									// 2) Si encuentra registro ingresamos a esta opción.
		x -> {								// 3) X contiene el resultado de la búsqueda.
			String autor = parametro.get("autor");			// 4) Buscamos el valor enviado. En este caso autor.
			if(!StringUtils.isEmpty(autor)) {			// 5) Verificamos que no sea nulo.
				x.setAutor(autor);				// 6) Actualizamos atributo.
				return libroRepository.save(x);			// 7) Grabamos cambios.
			} else {						// 8) En caso no se ha enviado autor.
				throw new 					
				LibroUnSupportedFieldPatchException(		// 9) Llamamos a la excepción LibroUnSupportedFieldPatchException.
					parametro.keySet()			// 10) Enviamos como parámetro el valor enviado al servicio.
				);
			}

		}
	)
	.orElseGet(								// 11) En caso no se encuentre registro.
		() -> {								// 12) No recuperamos ningún parámetro.
			throw new LibroNotFoundException(id);			// 13) Enviamos la excepción LibroNotFoundException.
		}
	);

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
