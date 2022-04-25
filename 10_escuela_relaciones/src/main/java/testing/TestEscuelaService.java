package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.EscuelaService;

@ExtendWith(SpringExtension.class)
//le decimos a spring que durante el arranque utilice solo esta clase de configuracion
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestEscuelaService {
	
	@Autowired
	EscuelaService service;	
	
	@Test
	void testAlumnosCurso() {
		//Los alumnos con curso python son 3
		assertEquals(3, service.alumnosCurso("python").size());
	}
	
	@Test
	void testAlumnosCursoDuracion() {
		assertEquals(3, service.alumnosCursoDuracion(70).size());
		
	}
	
	@Test
	void testCursoMatriculadoAlumno() {
		assertEquals("java", service.cursoMatriculadoAlumno("2222B").getDenominacion());
		
	}
	
	@Test
	void testAlumnosSenior() {
		assertEquals(3, service.alumnosSenior(35).size());
	}
	
	@Test
	void testEdadMediaCurso() {
		assertEquals(25, service.edadMediaCurso("java"));
	}
	
	@Test
	void testPrecioCurso() {
		assertEquals(250, service.precioCurso("primero@gmail.com"));
	}

}
