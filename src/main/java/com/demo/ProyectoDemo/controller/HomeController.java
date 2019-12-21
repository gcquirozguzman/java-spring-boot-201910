package com.demo.ProyectoDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
    public String root() {
        return "index";
    }
	
	@GetMapping("/principal")
    public String principal() {
        return "index";
    }

    @GetMapping("/libro")
    public String userIndex() {
        return "libro/index";
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