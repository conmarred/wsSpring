package service;

import java.util.Date;
import java.util.List;

import dto.AlumnoDto;
import dto.CursoDto;


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
	
	AlumnoDto validarUsuario(String usuario, String password);
	List<CursoDto> cursosAlumno(String usuario);
	List<CursoDto> listarCursos();
	List<AlumnoDto> alumnosCurso(String nombreCurso);
	List<AlumnoDto> alumnos();
	//Ejericio 13
	boolean matricularAlumno(String usuario, Integer idCurso);
	boolean altaAlumno(AlumnoDto alumno);
	boolean altaCurso(CursoDto curso);
	List<CursoDto> findCursosFechaBetween(Date first, Date second);
	List<CursoDto> cursosPosiblesMatricularAlumno(String usuario);
}
