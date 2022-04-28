package controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import model.Curso;
import service.FormacionService;

@CrossOrigin("*")
@Controller
public class FormacionController {
	
	@Autowired
	FormacionService service;
	
	@PostMapping("Login")
	public String login(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		Alumno a = service.validarUsuario(usuario, password);
		if(a!=null) {
		return "menu";
		}else {
			return "errorLogin";
		}
	}
	
	/* AJAX */
	
	@GetMapping(value="Alumnos", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> alumnos(){
		return service.alumnos();
	}
	
	//http://localhost:8080/11_proyecto_formacion/AlumnosCurso?nombre=php
	@GetMapping(value="AlumnosCurso", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> alumnosCurso(@RequestParam("nombre") String nombreCurso){
		return service.alumnosCurso(nombreCurso);
	}
	
	@GetMapping(value="Cursos", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursos(){
		return service.listarCursos();
	}
	
	//http://localhost:8080/11_proyecto_formacion/CursosAlumno?usuario=admin
	@GetMapping(value="CursosAlumno", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursosAlumnos(@RequestParam("usuario") String usuario){
		return service.cursosAlumno(usuario);
	}
	
	
}
