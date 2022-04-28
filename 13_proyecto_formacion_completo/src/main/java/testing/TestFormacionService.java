package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import model.Alumno;
import model.Curso;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
//le decimos a spring que durante el arranque utilice solo esta clase de configuracion
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestFormacionService {
	
	@Autowired
	FormacionService service;	
	
	@Test
	void testValidarUsuario() {
		assertEquals("nuevo", service.validarUsuario("nuevo10", "nuevo10").getNombre());
		assertEquals(null, service.validarUsuario("nuevo10", "nuevo1"));

	}
	
	@Test
	void testCursosAlumnos() {
		assertEquals(3, service.cursosAlumno("aaa").size());
	}
	
	@Test
	void testListarCursos() {
		assertEquals(18, service.listarCursos().size());
	}
	
	@Test
	void testAlumnosCurso() {
		assertEquals(4, service.alumnosCurso("python").size());
	}
	
//	@Test
	//Este test lo realizo en la clase TestMatriculacion
	void testMatricularAlumno() {
		//Alumno alumno1 = new Alumno("conce", "marquez", "concemarquez", 23, "conce@email.es");
		service.matricularAlumno("aaa", 2);
		assertEquals(5, service.alumnosCurso("python").size());
	}
	
	//@Test
	void testAltaAlumno() {
		Alumno alumno = new Alumno("concemarq", "pass", "conce", 23, "conce@email.com");
		Alumno alumno2 = new Alumno();
		alumno2.setUsuario("admin");
		assertEquals(true, service.altaAlumno(alumno));
		assertEquals(false, service.altaAlumno(alumno2));

	}
	
	//@Test
	void testAltaCurso() {
		Calendar c2 = Calendar.getInstance();
		c2.set(2020, 11, 30);
		Date date = c2.getTime();
		Curso curso = new Curso(19, "cursoPrueba2", 120, 25.3, date);
		assertEquals(true, service.altaCurso(curso));
		Curso curso1 = new Curso();
		curso1.setIdCurso(1);
		assertEquals(false, service.altaCurso(curso1));
	}
	
	@Test
	void testFechas() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		//Los meses van de 0 a 11
		c1.set(2020, 0, 1);
		c2.set(2020, 11, 30);
		Date first = c1.getTime();
		Date second = c2.getTime();
		assertEquals(14, service.findCursosFechaBetween(first, second).size());
		
	}

}
