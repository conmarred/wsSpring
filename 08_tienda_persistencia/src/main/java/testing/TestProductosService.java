package testing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.ProductoService;


@ExtendWith(SpringExtension.class)
//le decimos a spring que durante el arranque utilice solo esta clase de configuracion
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestProductosService {
	@Autowired
	ProductoService service;
	
	@Test
	void testProducto() {
		assertEquals(11.6, service.buscarProductoId(4).getPrecio());
		assertNull(service.buscarProductoId(800));
	}
	
	@Test
	void testMedia() {
		assertEquals(54.43333333333334, service.PrecioMedioSeccion("hogar"));
	}

}
