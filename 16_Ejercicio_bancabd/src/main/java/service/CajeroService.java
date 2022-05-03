package service;

import java.util.Date;
import java.util.List;

import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;

public interface CajeroService {
	CuentaDto validarCuenta(int numeroCuenta);
	void ingreso(MovimientoDto movimiento);
	void extraccion(MovimientoDto movimiento);
	List<MovimientoDto> movimientosFechaBetween(Date first, Date second);
	List<ClienteDto> listaClientes();
}
