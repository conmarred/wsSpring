package service;

import java.util.Date;
import java.util.List;

import dto.AlumnoDto;
import dto.CursoDto;
import dto.MatriculaDto;


public interface FormacionService {
	
	AlumnoDto validarUsuario(String usuario, String password);
	List<CursoDto> cursosAlumno(String usuario);
	List<CursoDto> listarCursos();
	List<AlumnoDto> alumnosCurso(String nombreCurso);
	List<AlumnoDto> alumnos();
	boolean matricularAlumno(String usuario, Integer idCurso);
	boolean altaAlumno(AlumnoDto alumno);
	boolean altaCurso(CursoDto curso);
	//Ejercicio 15
	List<MatriculaDto> findCursosFechaBetween(Date first, Date second);
	List<CursoDto> cursosPosiblesMatricularAlumno(String usuario);
}
