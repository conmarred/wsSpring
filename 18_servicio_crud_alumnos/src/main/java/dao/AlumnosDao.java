package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Alumno;
/*servicio Crud Alumnos:

- Alta de alumnos a partir de JSON recibido en el cuerpo
- Recuperación de todos los alumnos
- Busqueda de alumno por id
- Eliminación de alumno por id
- Actualización de alumno. Cambiar el curso del alumno cuyo id se recibe
- recuperación de la lista de curso*/

public interface AlumnosDao extends JpaRepository<Alumno, Integer> {
	//opcion 1: se le da la lista de cursos ya filtrada
	/*@Query("select distinct(a.curso) from Alumno a")
	List<String> findCursos();*/
	//opcion 2: no se hace el metodo anterior y la capa de servicio filtra
	//a partir de la lista de cursos
}
