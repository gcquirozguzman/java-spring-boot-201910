# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 11: Inicio de sesión de formulario con Spring Security y Thymeleaf - FONTEND._

```
📢  Los principales beneficios de usar Thymeleaf en nuestro ambiente son los siguientes:
	
	-> Podemos prototipar sin necesidad de ejecutar la aplicación. Es decir, modificando directamente el documento y probándolo desde un navegador.
	-> Si nuestra aplicación está corriendo y solo modificamos las plantillas, no es necesario volver a desplegarla, cambiando la propiedad cache a false.
	
		thymeleaf:                                 
	    		cache: false
	
	-> El dialecto que tiene Thymeleaf para trabajar con Spring, es mucho más potente y rico en funcionalidades que la librería de etiquetas de JSTL.
	
📢  Crearemos la siguiente estructura dentro de la carpeta resources.

	resources
		static				// Contiene  imágenes, JavaScript o CSS
			css			// CSS
			img			// Imágenes
			js			// JavaScript
		templates
			dashboard		// Páginas del dashboard
			error			// Páginas en caso error
			generico		// Páginas genéricas
			libro			// Páginas del libro

📢  Se debe tener en cuenta que tanto los elementos JS como los CSS deberían ser minificados. Esto significa, remover todo lo que los navegadores no necesitan, por ejemplo, los comentarios, las tabulaciones, algunos espacios y los saltos de línea.
📢  La primera ventaja de minificar es que este proceso reduce el tamaño del archivo, por lo que cuando se visite a la página, demorará menos en descargar los recursos, esto ayuda a acelerar la carga de la web.
📢  Para minificar un JS podemos usar la siguiente herramienta:
	
	https://javascript-minifier.com/
	
📢  Para minificar un CSS podemos usar la siguiente herramienta:

	https://www.cleancss.com/css-minify/
	
📢  Se creará la primera página que cargará el aplicativo en "resources>templates>index.html".
📢  En la cabecera se agregará las siguientes líneas para que el documento reconozca el dialecto de Thymeleaf.

	<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      	xmlns:th="http://www.thymeleaf.org">

📢  En caso necesitemos agregar una librería descargada por Maven, podemos agregarla de la siguiente forma.

	<link th:href="@{/webjars/font-awesome/5.11.2/css/all.min.css}" rel="stylesheet" type="text/css"/>

📢  O en su defecto, en caso de librerías externas o dentro de las carpetas del proyecto.

	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">

📢  En caso necesitemos agregar un documento JS.
	
	<script th:src="@{/webjars/jquery/3.2.1/jquery.min.js}"></script>

📢  O en su defecto, en caso de JS externas o dentro de las carpetas del proyecto.
	
	<script src="js/index.min.js"></script>

📢  Dentro de todo el documento "index.html", se creará un botón que nos permita redireccionar al login. Esto lo lograremos con la  propiedad href. 
	
	<a href="login">Login</a>

📢  Recordemos que en el controlador "HomeController" tenemos ya preparado el requests login, el cual redirigirá a la página "login.html".
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

📢  Cuando implementemos las demás interfaces tendremos en cuenta que habrán elementos que se repitan, por ejemplo cabecera, pie de página o incluso las librerías que los html van a usar siempre. A fin de simplificar código, ordenaremos estos elementos dentro de la carpeta "genérico".

	generico
	   -> footer.html		// Pie de página. Acá agregaremos en la cabecera "thymeleaf-extras-springsecurity" con la finalidad que se puedan reconocer las propiedades de Spring Security en el documento HTML.
	   -> header.html		// Cabecera donde estarán las principales opciones del Dashboard
	   -> layout_login.html		// Acá importaremos el footer mencionado anteriormente. Esto lo usaremos solo en el login.
	   -> layout.html		// Acá importaremos el footer y header mencionado anteriormente. Esto lo usaremos en todas las paginas menos en la de login.

📢  Se detalla footer.html.
	
	<!DOCTYPE HTML>
	<html xmlns:th="http://www.thymeleaf.org"
	      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">				// Para reconocer las propiedades de Spring Security.
	<body>
	<th:block th:fragment="footer">										// Etiqueta que englobara nuestro contenido.
	    <footer>
		<div class="container">
		    <p>
			Copyright &copy; Shester 2019
			<span sec:authorize="isAuthenticated()" style="display: inline-block;">			// En esta linea validamos si es que el usuario esta autentificado. Solo si es que lo está, se mostrará este elemento.
			    | Logged user: <span sec:authentication="name"></span> |				// Concatenamos el username.
			    Roles: <span sec:authentication="principal.authorities"></span> |			// Concatenamos el rol
			    <a th:href="@{/logout}">Sign Out</a>
			</span>
		    </p>
		</div>
	    </footer>

	    <script type="text/javascript" th:src="@{/webjars/jquery/3.2.1/jquery.min.js/}"></script>
	    <script type="text/javascript" th:src="@{/webjars/bootstrap/4.3.1/js/bootstrap.min.js}"></script>
	</th:block>
	</body>
	</html>

📢  Se detalla header.html

	<!DOCTYPE HTML>
	<html xmlns:th="http://www.thymeleaf.org">
	<body>
	<th:block th:fragment="header">								// Etiqueta que englobara nuestro contenido.
		<nav class="navbar navbar-expand-lg" id="mainNav">
		  ...
		</nav>
	</th:block>
	</body>
	</html>

📢  Se detalla layout.html. Tener en cuenta que layout_login.html solo se diferencia en un estilo importado y que no tiene header.html.

	<!DOCTYPE html>
	<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	      xmlns:th="http://www.thymeleaf.org">
	<head>
	    <meta charset="utf-8"/>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	    <meta name="viewport" content="width=device-width, initial-scale=1"/>
	    <link rel="stylesheet" type="text/css" th:href="@{/webjars/bootstrap/4.3.1/css/bootstrap.min.css}"/>
	    <link rel="stylesheet" type="text/css" th:href="@{/css/global.min.css}"/>
	    <title layout:title-pattern="$LAYOUT_TITLE - $CONTENT_TITLE">Demo</title>	// Se concatenará "Demo" + lo que se reciba en el documento que se invoque con la etiqueta title. Ejemplo "<title>Login</title>"
	    <link rel="shortcut icon" th:href="@{/img/icono.ico}" />
	</head>
	<body>
	    <th:block th:replace="generico/header :: header"/>				// Se importa contenido header.html
	    <header class="masthead-dashboard">						
		<th:block layout:fragment="content"/>					// Se agregará en esta posicion el elemento que tenga como nombre de contenido "content". Esto lo detallaremos en "login.html", que es donde se usará.
	    </header>
	    <th:block th:replace="generico/footer :: footer"/>				// Se importa contenido footer.html
	</body>
	</html>

📢  Ahora crearemos la página que realizara el login en "resources>templates>login.html".
	
	<!DOCTYPE html>
	<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	      xmlns:th="http://www.thymeleaf.org"
	      layout:decorate="~{generico/layout_login}">				// Importamos nuestro contenido genérico creado.
	<head>
	    <title>Login</title>							// Etiqueta title. Mencionado en layout.html.
	</head>
	<body>
		<div layout:fragment="content" th:remove="tag">				// Elemento con nombre de contenido "content". Mencionado en layout.html.
		    <form th:action="@{/login}" method="post">				// Detecta la accion del formulario y envia una peticion "/login". Recordar que ya ha sido declarada en SpringSecurityConfig.java - formLogin().
			<div th:if="${param.error}">					// Si la página retorna con un error se habilita este bloque.
			    Usuario o Password incorrecto.
			</div>
			<div th:if="${param.logout}">					// Si el usuario selecciona logout se habilita este bloque.
			    Usted ha sido desconectado.
			</div>
			<div class="form-group">
			    <label for="username">Usuario</label>:
			    <input type="text"
				   id="username"
				   name="username"
				   class="form-control">
			</div>
			<div class="form-group">
			    <label for="password">Password</label>:
			    <input type="password"
				   id="password"
				   name="password">
			</div>
			<div class="form-group">		    
				<a th:href="@{/}">
					<input 
					class="form-control btn btn-success"
					value="Regresar">
				</a>
				<input type="submit"					// Botón para envío de petición de formulario.
					name="login-submit"
					id="login-submit"                                  
					class="form-control btn btn-primary"
					value="Validar">
			</div>		
		    </form>
		</div>
	</body>
	</html>
	
	
📢  En caso el usuario y clase sean correctos se redirigirá a "http://localhost:8080/ProyectoDemo/dashboard". Recordar la configuración en "SpringSecurityConfig"
	
	.formLogin()
	    .loginPage("/login")
	    .defaultSuccessUrl("/dashboard", true)					// Requests solicitado en caso de acceso.
	    .permitAll()

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
