package service;

import java.util.Date;
import java.util.List;

import dto.CuentaDto;
import dto.MovimientoDto;

public interface CajeroService {
	CuentaDto validarCuenta(int numeroCuenta);
	void ingreso(CuentaDto cuenta, Integer cantidad);
	void extraccion(CuentaDto cuenta, Integer cantidad);
	List<MovimientoDto> movimientosFechaBetween(int idCuenta, Date first, Date second);
	boolean transferencia(CuentaDto cuenta1, Integer cantidad, CuentaDto cuenta2);
	
}
