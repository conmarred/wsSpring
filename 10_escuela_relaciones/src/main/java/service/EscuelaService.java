package service;

import java.util.List;

import model.Alumno;
import model.Curso;


public interface EscuelaService {
	List<Alumno> alumnosCurso(String nombreCurso);
	List<Alumno> alumnosCursoDuracion(int duracionMax);

	Curso cursoMatriculadoAlumno(String dni);
	
	//lista de cursos en los que esta matriculados alumnos con edad superior al valor recibido
	List<Curso> alumnosSenior(int edad); //lista de cursos en los que esta matriculados alumnos con edad superior al valor recibido
	
	//devulve la edad media de los alumnos matriculados en el curso
	double edadMediaCurso(String nombreCurso);
	
	//devuelve el precio del curso en el que esta matriculado el alumno cuyo email se recibe
	double precioCurso(String email);
}
