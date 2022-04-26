package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;

@Repository
public class AlumnosDaoImpl implements AlumnosDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Alumno findByUsuarioAndPassword(String usuario, String password) {
		String jpql = "select a from Alumno a where a.usuario= :usuario";
		TypedQuery<Alumno> query=entityManager.createQuery(jpql, Alumno.class);
		query.setParameter("usuario", usuario);
		Alumno aux = query.getSingleResult();
		if(aux.getPassword().equals(password)) {
			return aux;
		}else {
			return null;
		}
	}

	@Override
	public List<Alumno> findByCurso(String nombreCurso) {
		String jpql = "select a from Alumno a join a.cursos c where c.nombre=:nombre";
		TypedQuery<Alumno> query=entityManager.createQuery(jpql, Alumno.class);
		query.setParameter("nombre", nombreCurso);
		List<Alumno> resultado = query.getResultList();
		return resultado;
	}

	@Override
	public Alumno findById(String usuario) {
		return entityManager.find(Alumno.class, usuario);
	}
	
	@Transactional
	@Override
	public void update(Alumno alumno) {
		entityManager.merge(alumno);
	}

}
