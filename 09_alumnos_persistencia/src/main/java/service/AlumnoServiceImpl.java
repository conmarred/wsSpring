package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	@Override
	public void alta(Alumno alumno) {
		entityManager.persist(alumno);
		
	}

	@Override
	public List<Alumno> listar(String curso) {
		TypedQuery<Alumno> query=entityManager.createNamedQuery("Alumno.findAll", Alumno.class);
		return query.getResultList();		
	}

	@Override
	public boolean existeAlumno(String nombre) {
		TypedQuery<Alumno> query=entityManager.createNamedQuery("Alumno.findByNombre", Alumno.class);
		query.setParameter("nombre", nombre);
		List<Alumno> lsAux = query.getResultList();	
		boolean existe =false;
		if(lsAux.size()>0) {
			existe = true;
		}
		return existe;
	}

	@Override
	public List<String> listaCursos() {
		String jpql = "select distinc a.curso from Alumno a";
		return entityManager.createQuery(jpql, String.class).getResultList();
	}

	

}
