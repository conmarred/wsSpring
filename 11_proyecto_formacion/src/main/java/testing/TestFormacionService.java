package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import model.Alumno;
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
		Alumno aux = new Alumno("aaa", "sseeeee", "ordenador", 45, "ss@dd.es");
		assertEquals(3, service.cursosAlumno(aux).size());
	}
	
	@Test
	void testListarCursos() {
		assertEquals(18, service.listarCursos().size());
	}
	
	@Test
	void testAlumnosCurso() {
		assertEquals(5, service.alumnosCurso("python").size());
	}
	
//	@Test
	//Este test lo realizo en la clase TestMatriculacion
	void testMatricularAlumno() {
		//Alumno alumno1 = new Alumno("conce", "marquez", "concemarquez", 23, "conce@email.es");
		service.matricularAlumno("aaa", 2);
		assertEquals(5, service.alumnosCurso("python").size());
	}

}
