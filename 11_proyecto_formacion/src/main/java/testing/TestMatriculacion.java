package testing;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
//le decimos a spring que durante el arranque utilice solo esta clase de configuracion
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestMatriculacion {
	@Autowired
	FormacionService service;
	
	@Test
	void testMatriculacion() {
		service.matricularAlumno("test1", 1);
		assertEquals(9, service.alumnosCurso("java 10").size());
	}

}
