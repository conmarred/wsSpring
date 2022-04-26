package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import model.Curso;

//notacion que le dice a spring que cree un objeto para acceso a datos
@Repository
public class CursosDaoImpl implements CursosDao {
	//Aqui es donde vamos a acceder a datos
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public Curso findById(Integer id) {
		return entityManager.find(Curso.class, id);
	}

	@Override
	public List<Curso> findAll() {
		String jpql ="select distinct(c) from Curso c";
		TypedQuery<Curso> query=entityManager.createQuery(jpql, Curso.class);
		List<Curso> resultado = query.getResultList();
		return resultado;
	}

	@Override
	public List<Curso> findByAlumno(String usuario) {
		String jpql = "select c from Curso c join c.alumnos a where a.usuario=:usuario";
		TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}

}
