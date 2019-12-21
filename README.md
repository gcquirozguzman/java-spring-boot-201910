# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 3: Creación de anotaciones para validaciones._

```
📢 Creamos el siguiente paquete.

	com.demo.ProyectoDemo.validator
	
📢 Dentro del paquete com.demo.ProyectoDemo.validator se creará la annotation Clasificacion.java.
	
	message() - Mensaje que se mostrará cuando los datos de entrada no sean válidos.
	groups() - Permite dividir las anotaciones en diferentes grupos para aplicar diferentes validaciones a cada grupo.
	payload() - Transporta información de metadatos consumida por un cliente de validación
	
	@Target - Donde se pueden usar nuestras anotaciones
	@Retention - Cómo se almacena la anotación marcada. RUNTIME = tiempo de ejecución.
	@Constraint : Especifica las clases que implementan la restricción.
	
📢 Dentro del paquete com.demo.ProyectoDemo.validator se creará la clase ClasificacionValidator.java.
📢 Se creará una lista de diferentes tipos permitidos. Para nuestro caso: "TIPO A" y "TIPO B".
📢 Implementamos a la clase ConstraintValidator<Clasificacion, String>
📢 Sobrescribimos el método isValid, solicitando que retorne si es que el valor enviado tiene coincidencia (clasificacionAceptada.contains(value))
📢 En la anotación creada con nombre Clasificacion.java actualizamos @Constraint con la clase que acabamos de crear:

	@Constraint(validatedBy = ClasificacionValidator.class)

📢 Se agrega la anotación creada a la propiedad clasificación.

	@Clasificacion
	private String clasificacion;
	
```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
