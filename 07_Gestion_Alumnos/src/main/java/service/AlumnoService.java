package service;

import java.util.List;

import model.Alumno;

public interface AlumnoService {

	void alta(Alumno producto);
	
	List<Alumno> listar(String curso);
	
	boolean existeAlumno(String nombre);
	
	List<String> listaCursos();
}
