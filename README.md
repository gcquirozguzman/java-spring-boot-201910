# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 2: Creación de Entidades._

```
📢 Se trabajará con H2, que nos provee Spring Boot, el cual es una base de datos rápida en memoria.
📢 Se trabajará con Spring Data JPA, teniendo en cuenta que su objetivo es simplificar al desarrollador la persistencia de datos contra distintos repositorios de información. Por esta clase, solo se usarán las anotaciones en el entity.
📢 Para agregar ambas dependencias, adicionamos las siguientes dependencias en pom.xml.
	
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>com.h2database</groupId>
		<artifactId>h2</artifactId>
		<scope>runtime</scope>
	</dependency>

📢 Creamos el siguiente paquete.

	com.demo.ProyectoDemo.entity
	
📢 Dentro del paquete com.demo.ProyectoDemo.entity se creará la clase LibroEntity. Dentro de esta clase se manejarán las siguientes anotaciones.
	
	@Entity - Usada para declarar a la clase como entidad.	
	@Id - Usada para marcar atributo como ID.
	@GeneratedValue - Usada para generar un ID de forma automática.
	@NotEmpty - Usada para evitar que no se ingresen nulos para texto.
	@NotNull - Usada para evitar que no se ingresen nulos para números.
	@DecimalMin - Usada para indicar el mínimo valor aceptado.
	
📢 Se procede a crear los métodos de accesos a los atributos privado.
📢 Se procede a crear los constructores.
```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
