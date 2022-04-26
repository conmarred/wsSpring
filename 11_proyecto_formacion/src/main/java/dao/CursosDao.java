package dao;

import java.util.List;

import model.Curso;

public interface CursosDao {
	//Metodos que la capa dao debe exponer a la capa servicio
	
	
	Curso findById(Integer id);
	List<Curso> findAll();
	//Lo que reciben los métodos es la PK
	List<Curso> findByAlumno(String usuario);
}
