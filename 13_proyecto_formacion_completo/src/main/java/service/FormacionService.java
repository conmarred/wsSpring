package service;

import java.util.Date;
import java.util.List;

import model.Alumno;
import model.Curso;


public interface FormacionService {
	/*
	 * ENUNCIADO
11.- Partiendo de la capa de persistencia de formacion, queremos incorporar las siguiente funcionalidades:
	- Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno.
	- Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno.
	- Lista de cursos.
	- Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso.
	- Matricular alumno. A partir del usuario e idCurso, el alumno se matriula en dicho curso.
	 *
	 */
	
	Alumno validarUsuario(String usuario, String password);
	List<Curso> cursosAlumno(String usuario);
	List<Curso> listarCursos();
	List<Alumno> alumnosCurso(String nombreCurso);
	List<Alumno> alumnos();
	//Ejericio 13
	boolean matricularAlumno(String usuario, Integer idCurso);
	boolean altaAlumno(Alumno alumno);
	boolean altaCurso(Curso curso);
	List<Curso> findCursosFechaBetween(Date first, Date second);
	List<Curso> cursosPosiblesMatricularAlumno(String usuario);
}
