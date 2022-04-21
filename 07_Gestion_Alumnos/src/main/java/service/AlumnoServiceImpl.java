package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Alumno;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	JdbcTemplate template;
	
	@Override
	public void alta(Alumno alumno) {
		String sql = "insert into alumnos(nombre,curso,nota) values(?,?,?)";
		template.update(sql, alumno.getNombre(), alumno.getCurso(), alumno.getNota());
	}

	@Override
	public List<Alumno> listar(String curso) {
		String sql="select * from alumnos where curso=?";
		return template.query(sql,
				(rs, f)->new Alumno(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("curso"),
						rs.getDouble("nota")),
				curso);
		
	}

	@Override
	public boolean existeAlumno(String nombre) {
		String sql = "select * from alumnos where nombre=?";
		List<Alumno> alumnos = template.query(sql,
				(rs, f)->new Alumno(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("curso"),
						rs.getDouble("nota")),
				nombre);
		return alumnos.size()>0;
	}

	@Override
	public List<String> listaCursos() {
		String sql = "select distinct(curso) from alumnos";
		return template.query(sql, 
				(r,f)->r.getString(1));
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public List<Alumno> listar(String curso) {
//		String sql="select * from productos where seccion=?";
//		return template.query(sql,
//				(rs, f)->new Alumno(rs.getInt("id"),
//						rs.getString("nombre"),
//						rs.getString("seccion"),
//						rs.getDouble("precio"),
//						rs.getInt("stock")),
//				seccion);
//	}





}
