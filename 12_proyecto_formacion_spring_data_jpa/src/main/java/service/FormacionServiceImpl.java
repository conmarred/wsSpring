package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;

@Service
public class FormacionServiceImpl implements FormacionService{
	
	AlumnosDao alumnosDao;
	
	CursosDao cursosDao;
	
	public FormacionServiceImpl(@Autowired AlumnosDao alumnosDao, @Autowired CursosDao cursosDao) {
		this.alumnosDao=alumnosDao;
		this.cursosDao=cursosDao;
	}
	@PostMapping(value="Login")
	@Override
	public Alumno validarUsuario(String usuario, String password) {
		return alumnosDao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public List<Curso> cursosAlumno(String usuario) {
		return cursosDao.findByAlumno(usuario);
	}

	@Override
	public List<Curso> listarCursos() {
		return cursosDao.findAll();
	}

	@Override
	public List<Alumno> alumnosCurso(String nombreCurso) {
		return alumnosDao.findByCurso(nombreCurso);
	}

	//@Transactional esto en el dao que es donde modifico bbdd
	@Override
	public boolean matricularAlumno(String usuario, Integer idCurso) {
		//findById es heredado de CrudRepository, que devuelve el dato pero de tipo optional
		//entoces poner .get() o .orElse(null)
		Optional<Alumno> alumno = alumnosDao.findById(usuario);
		Optional<Curso> curso = cursosDao.findById(idCurso);
		//otra manera de hacerlo es esta (para no usar null)
		//preguntamos si esta presente y podemos llamar el get para obtener el resultado
		if(curso.isPresent()&&alumno.isPresent()) {
			Alumno al = alumno.get();
			al.getCursos().add(curso.get());
			alumnosDao.save(alumno.get());
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<Alumno> alumnos() {
		return alumnosDao.findAll();
	}
	
	
}
