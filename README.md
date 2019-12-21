# Demo: Spring Boot + Java

Demo básico de un proyecto Java con Spring Boot

### Construcción del Proyecto 📦

_Clase 10: Inicio de sesión de formulario con Spring Security y Thymeleaf - BACKEND._

```
📢  Antes de iniciar necesitamos agregar las propiedades y dependencias adicionales de Thymeleaf.
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>					
		<thymeleaf.version>3.0.10.RELEASE</thymeleaf.version>							
	    	<thymeleaf-layout-dialect.version>2.3.0</thymeleaf-layout-dialect.version>				
	    	<thymeleaf-spring-data-dialect.version>3.3.1</thymeleaf-spring-data-dialect.version>			
	    	<thymeleaf-extras-springsecurity5.version>3.0.3.RELEASE</thymeleaf-extras-springsecurity5.version>	
	    	<thymeleaf-extras-java8time.version>3.0.1.RELEASE</thymeleaf-extras-java8time.version>			
	</properties>
	
	<dependency>
		<groupId>org.thymeleaf.extras</groupId>
		<artifactId>thymeleaf-extras-springsecurity5</artifactId>	// Integracion con Spring Security 5.
	</dependency>
	<dependency>
		<groupId>org.thymeleaf.extras</groupId>
		<artifactId>thymeleaf-extras-java8time</artifactId>		// Para trabajar con nuevas clases de fecha Java 8.
	</dependency>
	<dependency>
		<groupId>io.github.jpenren</groupId>
		<artifactId>thymeleaf-spring-data-dialect</artifactId>
		<version>${thymeleaf-spring-data-dialect.version}</version>	// Este dialecto utiliza los elementos de paginación Spring Data para crear componentes de paginación y clasificación en el estilo Bootstrap.
	</dependency>
	<dependency>
		<groupId>nz.net.ultraq.thymeleaf</groupId>
		<artifactId>thymeleaf-layout-dialect</artifactId>		// Para reconocer dialecto del diseño.
	</dependency>

📢  También es necesario agregar otras librerías adicionales para las plantillas (JQuery, Bootstrap y JQuery Easing).

	<!-- Bootstrap -->					// Framework para crear interfaces web con CSS y JavaScript, permite adaptar la interfaz al sitio web o dispositivo.
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>bootstrap</artifactId>
            <version>4.3.1</version>
        </dependency>
	
        <!-- JQuery -->						//  Librería de JavaScript que permite agregar interactividad y efectos visuales.
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.2.1</version>
        </dependency>
	
	<!-- JQuery Easing -->					// Facilita hacer efectos en jQuery
	<dependency>
	    <groupId>org.webjars</groupId>
	    <artifactId>jquery-easing</artifactId>
	    <version>1.4.1</version>
	</dependency>
	
	<!-- Font Awesome -->					// Conjunto de herramientas de fuentes e iconos basado en CSS y MENOS		
	<dependency>
	    <groupId>org.webjars</groupId>
	    <artifactId>font-awesome</artifactId>
	    <version>5.11.2</version>
	</dependency>

📢  Agregaremos a "application.yml" las siguientes propiedades para terminar de configurar Thymeleaf.

	thymeleaf:                                 
	    cache: false                             // Admite cambios sin necesidad de reinicio.
	    check-template: true                     // Compruebe que la plantilla existe antes de representarla.
	    check-template-location: true            // Comprueba que existe la ubicación de las plantillas.
	    content-type: text/html                  // Especifica el valor de tipo de contenido.
	    enabled: true                            // Habilitar la resolución de vista de mvc thymeleaf.
	    encoding: UTF-8                          // Especifica la codificación de plantilla.
	    mode: HTML                               // Modo de plantilla que se aplicará a las plantillas.
	    prefix: classpath:/templates/            // Especifica el prefijo que se antepone para ver los nombres al crear una URL.
	    suffix: .html                            // Especifica el sufijo que se agrega para ver los nombres al crear una URL.

📢  Crearemos una clase LoggingAccessDeniedHandler dentro de "com.demo.ProyectoDemo.security.web" para que pueda implementar AccessDeniedHandler, esto para el manejo de una excepción en caso se produzca un acceso denegado por permisos.

	@Component
 	public class LoggingAccessDeniedHandler implements AccessDeniedHandler {

    		private static Logger log = LoggerFactory.getLogger(LoggingAccessDeniedHandler.class);		// Necesario para escritura en log.

		@Override
		public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException, ServletException {
		       
		 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();		// Se recupera datos de la autentificación.
			if (auth != null) {
			    log.info("El usuario "								// Se escribe en log el detalle de la operación denegada.
					+ auth.getName()
				    + " intenta acceder al siguiente recursos protegidos: "
				    + request.getRequestURI());
			}
			response.sendRedirect(request.getContextPath() + "/access-denied");			// Retornamos al usuario a página con mensaje de acceso denegado.
    		}
	}


📢  Adicionaremos permisos en la clase SpringSecurityConfig y agregaremos la clase creada LoggingAccessDeniedHandler.
	
	@Autowired
    	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
    	protected void configure(HttpSecurity http) throws Exception {

		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(							// Se adicionan permisos.
			"/",							// Permiso para la ruta "/"
			"/js/**",						// Permiso para la carpeta "js"
			"/css/**",						// Permiso para la carpeta "css"
			"/img/**",						// Permiso para la carpeta "img"
			"/webjars/**").permitAll()				// Permiso para la carpeta "webjars"
		.antMatchers("/libro/**").hasRole("ADMIN")			// Solo ADMIN puede acceder.
		.antMatchers(HttpMethod.GET, "/libro/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/libro").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/libro/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PATCH, "/libro/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/libro/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()							
		    .loginPage("/login")					// Se asigna una pagina para login
		    .defaultSuccessUrl("/dashboard", true)			// Si usuario y clave es correcta se redirecciona a "/dashboard"
		    .permitAll()						
		.and()
		.logout()							
		    .invalidateHttpSession(true)				// Para invalidar HttpSessionen el momento del cierre de sesión.
		    .clearAuthentication(true)					// Si es true borra la autentificacion. 
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	// Request que activa el cierre de sesión.
		    .logoutSuccessUrl("/login?logout")				// En caso de logout se redirecciona a "/login?logout".
		    .permitAll()						
		.and()
		.exceptionHandling()						
		    .accessDeniedHandler(accessDeniedHandler)			// En caso que usuario acceda a recurso no permitido, lanza esta excepción.
		.and()
		.csrf().disable();
	}
	
📢  Pasaremos a declarar los requests como "/dashboard", "/login" y demás que vamos a necesitar.
📢  Crearemos una clase HomeController dentro de "com.demo.ProyectoDemo.controller". Esta clase la emplearemos como un web controller. 
	
	@Controller
	public class HomeController {
		
		@GetMapping("/")
	    	public String root() {
			return "index";
	    	}

		@GetMapping("/principal")	// Se llama: http://localhost:8080/ProyectoDemo/principal  
	    	public String principal() {	
			return "index";		// Método retorna a index.html
	    	}

	    	@GetMapping("/libro")		// Se llama: http://localhost:8080/ProyectoDemo/libro  
	    	public String userIndex() {
			return "libro/index";	// Método retorna a libro/index.html
	    	}

	    	@GetMapping("/dashboard")
	    	public String dashboardIndex() {
			return "dashboard/index";
	    	}

	    	@GetMapping("/login")
	    	public String login() {
			return "login";
	    	}

	    	@GetMapping("/acceso-denegado")
	    	public String accessDenied() {
			return "/error/acceso-denegado";
	    	}

	}

📢  En la siguiente clase agregaremos los componentes frontend para probar el formulario.

```

## Autores ✒️

* **Gian Carlo Quiroz Guzmán** - *Proyecto Demo* - [gcquirozguzman](https://github.com/gcquirozguzman)



⌨️ con ❤️ por [gcquirozguzman](https://github.com/gcquirozguzman) 😊
