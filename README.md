# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 7: Creando registros en H2, permiso por perfil de componente y pruebas de servicios._

```
📢  Ingresamos a nuestra clase principal ProyectoDemoApplication.
📢  Necesitamos que se ejecute un método cuando arranque del aplicativo, para esto usaremos CommandLineRunner. Creamos un método "iniciarBaseDatos" que lo retorne. 
📢  Agregamos como parámetro a PersonaRepository. Esto porque cuando inicie el aplicativo necesitamos que ingrese registros a la tabla LibroEntity.

	return args -> {
		libroRepository.save(new LibroEntity("Autor 1", "Libro 2011", "TIPO A", new BigDecimal("10")));
	};
	
📢  Podemos agregarle un perfil a este método creado. Para esto ingresamos a application.properties y agregamos la siguiente linea:
	
	spring.profiles.active=ambiente_desarrollo

📢  Al método creado le agregamos @Profile("ambiente_desarrollo"). Esto para indicar que solo para el perfil ambiente_desarrollo, se ejecutará el método "iniciarBaseDatos"

📢  Para probar los servicios se pueden crear o importar desde POSTMAN con la siguiente ruta (Clase 07).

	https://www.getpostman.com/collections/6ed42c8d9fe314e1b3ec

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
