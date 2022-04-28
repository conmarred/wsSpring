package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, String>{
	
	Alumno findByUsuarioAndPassword(String usuario, String password);
	@Query("select a from Alumno a join a.cursos c where c.nombre=?1")
	List<Alumno> findByCurso(String nombreCurso);
	

	/*
	 * Ahora no vamos a necesitar las clases del paquete Dao
	 * Ya que esa funcionalidad la va a implementar Spring
	 * Y solo nos quedamos con las interfaces
	 * Las interfaces ahora extienden de //JpaRepository<Tipo de la entidad, Tipo de la PK>
	 * Los metodos que desaparecen porque ya lo implementa spring:
	 * Desaparece findAll porque ya lo hace spring
	 * Actualizar alumno tambien (save)
	 * Buscar alumno por id
	 * JpaRepository no me dará los siguientes ya que son mas especificos
	 * Alumno findByUsuarioAndPassword(String usuario, String password);
	 * List<Alumno> findByCurso(String nombreCurso);
	 * Al llamarlo de esa manera spring sabe implementarlo
	 * findByCurso necesita datos de la relacion (join) asi que la query se la doy yo
	 */
}
