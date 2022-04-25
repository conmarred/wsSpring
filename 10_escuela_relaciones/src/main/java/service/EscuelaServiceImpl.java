package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import model.Alumno;
import model.Curso;

@Service
public class EscuelaServiceImpl implements EscuelaService{
	@PersistenceContext
	EntityManager entityManager;
	
	//Se trata de un join implicito
	@Override
	public List<Alumno> alumnosCurso(String nombreCurso) {
		String jpql = "select a from Alumno a where a.curso.denominacion= :curso";
		TypedQuery<Alumno> query=entityManager.createQuery(jpql, Alumno.class);
		query.setParameter("curso", nombreCurso);
		List<Alumno> resultado = query.getResultList();
		return resultado;
	}

	@Override
	public List<Alumno> alumnosCursoDuracion(int duracionMax) {
		String jpql = "select a from Alumno a where a.curso.duracion<=:duracion";
		TypedQuery<Alumno> query=entityManager.createQuery(jpql, Alumno.class);
		query.setParameter("duracion", duracionMax);
		List<Alumno> resultado = query.getResultList();
		return resultado;
	}

	//Se trata de un join explicito
	//Cuando afecta al lado muchos (es una coleccion)
	@Override
	public Curso cursoMatriculadoAlumno(String dni) {
		String jpql = "select c from Curso c join c.alumnos a where a.dni= :dni";
		TypedQuery<Curso> query=entityManager.createQuery(jpql, Curso.class);
		query.setParameter("dni", dni);
		return query.getSingleResult();
	}

	@Override
	public List<Curso> alumnosSenior(int edad) {
		//distinct para quitar los duplicados
		String jpql = "select distinct(c) from Curso c join c.alumnos a where a.edad>=:edad";
		TypedQuery<Curso> query=entityManager.createQuery(jpql, Curso.class);
		query.setParameter("edad", edad);
		List<Curso> resultado = query.getResultList();
		return resultado;
	}

	@Override
	public double edadMediaCurso(String nombreCurso) {
		String jpql = "select avg(a.edad) from Alumno a join a.curso c where c.denominacion =:curso";
		TypedQuery<Double> query=entityManager.createQuery(jpql, Double.class);
		query.setParameter("curso", nombreCurso);
		//hay que tener cuidado con SingleResult porque si hay mas de uno o ninguno da excepcion
		return query.getSingleResult();
	}

	@Override
	public double precioCurso(String email) {
		String jpql ="select c.precio from Curso c join c.alumnos a where a.email=:email";
		TypedQuery<Double> query=entityManager.createQuery(jpql, Double.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

}
