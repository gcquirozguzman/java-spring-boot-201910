# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 5: Manejo de excepciones RuntimeException._

```
📢 Creamos el siguiente paquete.

	com.demo.ProyectoDemo.exception

📢  En el paquete com.demo.ProyectoDemo.exception creamos las siguientes clases:
	
	LibroNotFoundException - Clase creada para manejar excepciones cuando no se encuentra un libro buscado por un ID.
	LibroUnSupportedFieldPatchException - Clase creada para manejar excepciones cuando el tipo de dato ingresado no es el correcto.
	
📢  Tanto la clase LibroNotFoundException y LibroUnSupportedFieldPatchException heredan a RuntimeException el cual es empleado para el manejo de excepciones que ocurren dentro de la máquina virtual Java, durante el tiempo de ejecución.
📢  En ambas clases procedemos a crear un método público que reciba como parámetro a el valor que queramos y que nos sirva para retornar un mensaje.
📢  Terminaremos llamando al constructor de la clase RuntimeException con "SUPER", indicando el mensaje que queremos mostrar.

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 
