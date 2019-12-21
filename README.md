# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 4: Usando Spring Data JPA._

```
📢 Creamos el siguiente paquete.

	com.demo.ProyectoDemo.repository

📢 Creamos la clase LibroRepository y hacemos que herede el contenido de JpaRepository<LibroDTO, Long>. El primer parámetro que agregamos es Entity(LibroDTO) y el segundo es el tipo de atributo que tiene el ID de la clase(Long).
📢 Al heredar JpaRepository estamos incluyendo en LibroRepository todos los métodos que este trae, por ejemplo, save, delete, findAll, etc.
📢 Sin embargo, muchas veces necesito algún método muy específico, el cual no trae JpaRepository. Por ejemplo, si necesito que me retorne una lista por el nombre del autor y ordenado ascendentemente por el título, podríamos construirlo de la siguiente forma:
	
	List<LibroDTO> findByAutorOrderByTituloAsc(String autor);

📢 Podemos encontrar más información en el siguiente artículo.

	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
