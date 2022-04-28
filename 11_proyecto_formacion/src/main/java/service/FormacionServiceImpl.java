package service;

import java.util.List;

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
		Alumno alumno = alumnosDao.findById(usuario);
		Curso curso = cursosDao.findById(idCurso);
		if(alumno!=null && curso!=null) {
			alumno.getCursos().add(curso);
			alumnosDao.update(alumno);
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
