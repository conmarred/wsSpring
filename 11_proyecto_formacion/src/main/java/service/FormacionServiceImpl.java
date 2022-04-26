package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	@Override
	public Alumno validarUsuario(String usuario, String password) {
		return alumnosDao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public List<Curso> cursosAlumno(Alumno alumno) {
		return cursosDao.findByAlumno(alumno.getUsuario());
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
	public void matricularAlumno(String usuario, Integer idCurso) {
		Alumno alumno = alumnosDao.findById(usuario);
		Curso curso = cursosDao.findById(idCurso);
		if(alumno!=null && curso!=null) {
			alumno.getCursos().add(curso);
			alumnosDao.update(alumno);
		}
	}
	
	
}
