package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Matricula;
import model.MatriculaPK;

public interface MatriculasDao extends JpaRepository<Matricula, MatriculaPK>{
	@Query("select m from Matricula m where m.curso.fechaInicio between ?1 and ?2")
	List<Matricula> findMatriculasByFechaBetween(Date first, Date second);
}
