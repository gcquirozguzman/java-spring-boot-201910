# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 8: Thymeleaf para Configuración de Ambiente._

```

📢  Spring Boot nos permite usar archivos properties, yml(Thymeleaf), variables de entorno y argumentos de línea de comandos para externalizar la configuración.
📢  Para nuestro proyecto, podemos cambiar la libreta "application.properties" a "application.yml". La conversión de las propiedades internas se puede hacer manual, o por utilitarios como:
	
	https://localise.biz/free/converter/yml

📢  Para poder escribir en el archivo "application.yml", es recomendable usar un plugin para ver errores de de escritura en el archivo. Se puede instalar en Eclipse Marketplace el siguiente:

	Yaml Editor 1.5.0

📢  La estructura que manejaremos en el archivo yml será la siguiente:
	
	server:                                                            
	  port: 8080                                                       // Indica el puerto donde deseamos que inicie el aplicativo.
	  servlet:                                                         
	     contextPath: /ProyectoDemo                                    // Indica la ruta base (http://localhost:8080/ProyectoDemo).
																	 
	spring:                                                            
	  spring.main.banner-mode: off                                     // Off/On - Para que muestre o no el logo de Spring 
	  jpa:                                                             
	     properties.hibernate.format_sql: true                         // Da formato a las sentencias hibernate.
	  profiles:                                                        
	    active: ambiente_desarrollo                                    // Indica que ambiente esta activo																 
	---                                                                // Se inicia la configuración de log que pertenece a ambiente_desarrollo.
																	 
	spring:                                                            
	  profiles: ambiente_desarrollo                                    // Perfil al que pertenece este bloque de configuración.
	logging:                                                           
	  path: logs/dev                                                   // Carpeta donde se guardarán los log.
	  file: ${logging.path}/${spring.profiles}_profile_app.log         // Nombre del archivo.
	  file.max-history: 5                                              // Cantidad de archivos máximos a guardar.
	  file.max-size: 10MB                                              // Máximo tamaño de cada archivo. Luego de superado el tamaño se crea otro.
	  pattern:                                                         
	    console: "%d %-5level %logger : %msg%n"                        // Formato del log mostrado en consola.
	    file: "%d %-5level [%thread] %logger : %msg%n"                 // Formato del log guardado en archivo.
	  level:                                                           
	    root: INFO                                                     // Establece el nivel de registro para todo el grupo. 
	    com.demo: DEBUG						   // Establece el nivel de registro para el paquete. 
	    org.springframework.web: DEBUG                                 // Información referente a springframework.web
	    org.hibernate: DEBUG                                           // Información referente a hibernate.
    	    org.hibernate.type: TRACE					   // Información sobre los valores que se envian a la base de datos.

📢  Se debe tener en cuenta lo siguiente para el manejo de logs.

	DEBUG : Información interesante para desarrolladores, cuando intentan depurar un problema.
	INFO : Información interesante para el personal de soporte que intenta averiguar el contexto de un error determinado.
	WARN : Declaraciones que describen eventos o estados potencialmente dañinos en el programa.
	ERROR : Declaraciones que describen errores no fatales en la aplicación; este nivel se usa con bastante frecuencia para registrar excepciones manejadas.
	FATAL : Declaraciones que representan las condiciones de error más severas, que supuestamente resultan en la terminación del programa.

📢  Para evitar que los log se suban al repositorio, de deberá agradar la restricción en el archivo gitignore. Este archivo se encuentra a la altura de la carpeta src. Agregar las siguientes lineas.

	### Log Aplicativo ###
	logs/dev
	logs/prod

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
