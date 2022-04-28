package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Curso;
//JpaRepository<Tipo de la entidad, Tipo de la PK>
public interface CursosDao extends JpaRepository<Curso, Integer>{
	
	//Ahora solo sustituyo parametros (posiciones), no nombres
	@Query("select c from Curso c join c.alumnos a where a.usuario=?1")
	List<Curso> findByAlumno(String usuario);
}
