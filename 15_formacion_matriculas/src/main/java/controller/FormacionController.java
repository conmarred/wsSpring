package controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AlumnoDto;
import dto.CursoDto;
import dto.MatriculaDto;
import service.FormacionService;

@CrossOrigin("*")
@Controller
public class FormacionController {
	
	@Autowired
	FormacionService service;
	
	@PostMapping("Login")
	public String login(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		AlumnoDto a = service.validarUsuario(usuario, password);
		if(a!=null) {
		return "menu";
		}else {
			return "errorLogin";
		}
	}
	
	@GetMapping(value="AltaAlumno")
	public String altaAlumno(@ModelAttribute("alumno") AlumnoDto alumno) {
		boolean alta = service.altaAlumno(alumno);
		if(alta) {
			return "altaAlumno";
		}else {
			return "altaAlumnoError";
		}
	}
	
	@GetMapping(value="AltaCurso")
	public String altaCurso(@ModelAttribute("curso") CursoDto curso) {
		boolean alta = service.altaCurso(curso);
		if(alta) {
			return "altaCurso";
		}else {
			return "altaCursoError";
		}
	}
	
	
	/* AJAX */
	
	@GetMapping(value="Alumnos", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnos(){
		return service.alumnos();
	}
	
	//http://localhost:8080/11_proyecto_formacion/AlumnosCurso?nombre=php
	@GetMapping(value="AlumnosCurso", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnosCurso(@RequestParam("nombre") String nombreCurso){
		return service.alumnosCurso(nombreCurso);
	}
	
	@GetMapping(value="Cursos", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursos(){
		return service.listarCursos();
	}
	
	//http://localhost:8080/11_proyecto_formacion/CursosAlumno?usuario=admin
	@GetMapping(value="CursosAlumno", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursosAlumnos(@RequestParam("usuario") String usuario){
		return service.cursosAlumno(usuario);
	}
	
	//http://localhost:8080/13_proyecto_formacion_completo/CursosFechaBetween?first=2020-02-12&second=2020-08-23
	@GetMapping(value="CursosFechaBetween", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MatriculaDto> cursosFechaBetween(@RequestParam("first") @DateTimeFormat(pattern="yyyy-MM-dd") Date first, 
														@RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second){
		return service.findCursosFechaBetween(first, second);
	}
	
	@PostMapping(value="Matricular", produces = MediaType.APPLICATION_JSON_VALUE)
	public String matricular(@RequestParam("usuario") String usuario, @RequestParam("idCurso") Integer idCurso) {
		service.matricularAlumno(usuario, idCurso);
		return "";
	}
	
}
