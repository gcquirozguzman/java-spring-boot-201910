# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 9: Spring Security para Servicios._

```

📢 Para agregar Spring Security a nuestro proyecto, necesitaremos adicionar la siguiente dependencia.
	
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>

📢  Luego de haber agregado dicha dependencia, nuestro proyecto estará protegido. Podemos probar los servicios que tenemos hasta el momento, debemos importar desde el proyecto POSTMAN con la siguiente ruta (Clase 09).

	https://www.getpostman.com/collections/6ed42c8d9fe314e1b3ec
	
📢  Si intentamos consumir "ListarTodos", nos saltará la siguiente validación.

	{
	    "timestamp": "",
	    "status": 401,
	    "error": "Unauthorized",
	    "message": "Unauthorized",
	    "path": "/ProyectoDemo/libro/listarTodos"
	}

📢  Ahora, para poder consumir los servicios, necesitamos ingresar el usuario y password que nos ha asignado Spring Security. Por defecto el usuario es USER y la clase la debemos buscar en la consola:

	Using generated security password: (ACA ESTÁ EL PASSWORD)	

📢  Para poder ingresar el usuario y password, podemos ir al servicio requerido, en la pestaña "Authorization". Seleccionamos en TYPE, la opción "Basic Auth". Completamos lo solicitado en las casillas.

	Username: user
	Password: (Password obtenido en la consola)
	
📢  Si queremos una configuración más flexible, con múltiples usuarios y roles, iniciaremos agregando al proyecto lo siguiente.
📢  Creamos el siguiente paquete.

	com.demo.ProyectoDemo.config
	
📢  Dentro del paquete com.demo.ProyectoDemo.config se creará la clase SpringSecurityConfig.java.
📢  Iniciamos agregando la anotación @Configuration a la clase. Esto indica que una clase declara uno o más métodos @Bean.
📢  Extendemos WebSecurityConfigurerAdapter en la clase para habilitar la seguridad HTTP en Spring.
📢  Creamos el siguiente método. Esto con la finalidad de agregar 2 usuario y password.
	
	Usuario: user	Password: password	Rol Asignado: USER
	Usuario: admin	Password: password	Rol Asignado: USER - ADMIN

    	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}password").roles("USER")
		.and()
		.withUser("admin").password("{noop}password").roles("USER", "ADMIN");
	}

📢  Podemos probar agregando estas credenciales en POSTMAN para validar.
📢  Para agregar accesos a las diferentes solicitudes HTTP o asignar accesos a los usuarios por soles debemos implementar lo siguiente:

	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()							// Habilitar la autenticación básica
			.and()
			.authorizeRequests()						// Permite restringir el acceso
			.antMatchers(HttpMethod.GET, "/libro/**").hasRole("USER")	// Se asignan accesos por rol
			.antMatchers(HttpMethod.POST, "/libro/**").hasRole("ADMIN")	// Se asignan accesos por rol
			.antMatchers(HttpMethod.PUT, "/libro/**").hasRole("ADMIN")	// Se asignan accesos por rol
			.antMatchers(HttpMethod.PATCH, "/libro/**").hasRole("ADMIN")	// Se asignan accesos por rol
			.antMatchers(HttpMethod.DELETE, "/libro/**").hasRole("ADMIN")	// Se asignan accesos por rol
			.anyRequest().authenticated()					// Inica que cualquier solicitud debe autenticarse
			.and()
			.csrf().disable()						// Agrega soporte CSRF.
			.formLogin().disable();						// Especifica para admitir la autenticación basada en formularios.
    	}

📢  Podemos probar los accesos por roles en POSTMAN. Por ejemplo, si ingreso a la solicitud POST con el usuario "user", no me debería permitir la operación.

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
