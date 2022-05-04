package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dto.CuentaDto;
import service.CajeroService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestCajeroService {
	
	@Autowired
	CajeroService service;
	
	@Test
	void testValidarCuenta() {
		assertEquals(1000, service.validarCuenta(1000).getNumeroCuenta());
		assertEquals(null, service.validarCuenta(10000));
	}
	@Test
	void testSaldoCuenta() {
		CuentaDto cuenta = new CuentaDto(150, 100, "ahorro");
		assertEquals(100, cuenta.getSaldo());
	}
	@Test
	void testIngreso() {
		CuentaDto cuenta = new CuentaDto(1000, 41567, "ahorro");
		service.ingreso(cuenta, 100);
		assertEquals(41667, cuenta.getSaldo());
	}
	
	@Test
	void testExtraccion() {
		CuentaDto cuenta = new CuentaDto(1550, 100, "ahorro");
		service.extraccion(cuenta, 50);
		assertEquals(50, cuenta.getSaldo());
	}
	
	@Test
	void testTransferencia() {
		CuentaDto cuenta1 = new CuentaDto(1550, 1050, "ahorro");
		CuentaDto cuenta2 = new CuentaDto(2550, 100, "ahorro");
		service.transferencia(cuenta1, 1000, cuenta2);
		assertEquals(50, cuenta1.getSaldo());
		assertEquals(1100, cuenta2.getSaldo());
	}

}
