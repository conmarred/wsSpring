package testing;

import static org.mockito.Mockito.when;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;
import service.FormacionService;
import service.FormacionServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestFormacionMock {
	
	@Mock
	AlumnosDao alumnosDao;
	
	@Mock
	CursosDao cursosDao;
	
	List<Alumno> alumnos;
	List<Curso> cursos;
	
	FormacionService service;
	
	@BeforeEach
	void init() {
		cursos=List.of(new Curso(1,"curso1",100,10.0,null),
				new Curso(2,"curso2",200,20.0,null),
				new Curso(3,"curso3",300,30.0,null));
		
		alumnos=List.of(new Alumno("user1","pwd1","n1", 10,"e1",List.of(cursos.get(0),cursos.get(1))),
				new Alumno("user2","pwd2","n2", 20, "e2",List.of(cursos.get(0))),
				new Alumno("user3","pwd3","n3", 30,"e3",List.of(cursos.get(2))),
				new Alumno("user4","pwd4","n4", 10,"e4",List.of(cursos.get(0),cursos.get(2))));

		when(alumnosDao.findByUsuarioAndPassword("user1", "pwd1")).thenReturn(alumnos.get(0));
		when(alumnosDao.findByUsuarioAndPassword("user7", "aaa")).thenReturn(null);
		when(alumnosDao.findByCurso("curso1")).thenReturn(List.of(alumnos.get(0),alumnos.get(1),alumnos.get(3)));
		when(alumnosDao.findById("user3")).thenReturn(alumnos.get(2));
		
		when(cursosDao.findById(2)).thenReturn(cursos.get(1));
		when(cursosDao.findAll()).thenReturn(cursos);
		when(cursosDao.findByAlumno("user3")).thenReturn(alumnos.get(2).getCursos());

		service = new FormacionServiceImpl(alumnosDao, cursosDao);
	}
	@Test
	void testBuscarAlumno() {
		assertEquals("e1",service.validarUsuario("user1", "pwd1").getEmail());
		assertNull(service.validarUsuario("user7", "aaa"));
	}

	
	@Test
	void testCursosAlumno() {
		Alumno alumno = new Alumno();
		alumno.setNombre("user3");
		assertEquals(1, service.cursosAlumno(alumno).size());
	}
	
	@Test
	void testAlumnosCurso() {
		assertEquals(3, service.alumnosCurso("curso1").size());
	}
	
	@Test
	void testCursos() {
		assertEquals(3, service.listarCursos().size());
	}

}
