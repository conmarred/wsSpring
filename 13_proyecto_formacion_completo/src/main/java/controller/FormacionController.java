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
	
	@GetMapping(value="AltaAlumno")
	public String altaAlumno(@ModelAttribute("alumno") Alumno alumno) {
		boolean alta = service.altaAlumno(alumno);
		if(alta) {
			return "altaAlumno";
		}else {
			return "altaAlumnoError";
		}
	}
	
	@GetMapping(value="AltaCurso")
	public String altaCurso(@ModelAttribute("curso") Curso curso) {
		boolean alta = service.altaCurso(curso);
		if(alta) {
			return "altaCurso";
		}else {
			return "altaCursoError";
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
	
//	@GetMapping(value="CursosFechaBetween", produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody List<Curso> cursosFechaBetween(@RequestParam("fist") String first, @RequestParam("second") String second){
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//		Date date1;
//		Date date2;
//		try {
//			date1 = formato.parse(first);
//			date2 = formato.parse(second);
//			return service.findCursosFechaBetween(date1, date2);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//Mas sencillo de hacer con la notacion @DateTimeFormat para formatear la fecha 
	//http://localhost:8080/13_proyecto_formacion_completo/CursosFechaBetween?first=2020-02-12&second=2020-08-23
	@GetMapping(value="CursosFechaBetween", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursosFechaBetween(@RequestParam("first") @DateTimeFormat(pattern="yyyy-MM-dd") Date first, 
														@RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second){
		return service.findCursosFechaBetween(first, second);
	}
	
	
}
