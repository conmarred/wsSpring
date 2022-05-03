package service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityDto;
import dao.ClienteDao;
import dao.CuentaDao;
import dao.MovimientoDao;
import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;
import model.Cuenta;

@Service
public class CajeroServiceImpl implements CajeroService {

	@Autowired
	ConversorEntityDto conversor;
	
	ClienteDao clientesDao;
	CuentaDao cuentasDao;
	MovimientoDao movimientoDao;
	
	public CajeroServiceImpl(@Autowired ClienteDao clientesDao, @Autowired CuentaDao cuentasDao, @Autowired MovimientoDao movimientoDao) {
		this.clientesDao = clientesDao;
		this.cuentasDao = cuentasDao;
		this.movimientoDao = movimientoDao;
	}

	@Override
	public CuentaDto validarCuenta(int numeroCuenta) {
		Optional<Cuenta> c = cuentasDao.findById(numeroCuenta);
		if(c.isPresent()) {
			return conversor.cuentaToDto(c.get());
		}
		return null;
	}

	@Override
	public void ingreso(MovimientoDto movimiento) {
		movimientoDao.save(conversor.dtoToMovimiento(movimiento));
		Cuenta c = cuentasDao.findBynumeroCuenta(movimiento.getIdCuenta().getNumeroCuenta());
		cuentasDao.save(c);
	}

	@Override
	public void extraccion(MovimientoDto movimiento) {
		movimientoDao.save(conversor.dtoToMovimiento(movimiento));
		Cuenta c = cuentasDao.findBynumeroCuenta(movimiento.getIdCuenta().getNumeroCuenta());
		cuentasDao.save(c);
	}

	@Override
	public List<MovimientoDto> movimientosFechaBetween(Date first, Date second){
		return movimientoDao.findByMovimientoFechas(first, second)
				.stream().map(x -> conversor.movimientoToDto(x))
				.collect(Collectors.toList());
	}

	@Override
	public List<ClienteDto> listaClientes() {
		return clientesDao.findAll()
				.stream()
				.map(x -> conversor.clienteToDto(x))
				.collect(Collectors.toList());
	}
	


}
