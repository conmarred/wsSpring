package service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityDto;
import dao.ClienteDao;
import dao.CuentaDao;
import dao.MovimientoDao;
import dto.CuentaDto;
import dto.MovimientoDto;
import model.Cuenta;
import model.Movimiento;

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
	@Transactional
	@Override
	public void ingreso(CuentaDto cuenta, Integer cantidad) {
		Integer saldo = cuenta.getSaldo();
		cuenta.setSaldo(saldo + cantidad);
		conversor.cuentaToDto(cuentasDao.save(conversor.dtoToCuenta(cuenta)));
		Calendar fechaNow = Calendar.getInstance();
		movimientoDao.save(new Movimiento(0, fechaNow.getTime(), cantidad, "ingreso de "+cuenta.getNumeroCuenta(), conversor.dtoToCuenta(cuenta)));
	}
	@Transactional
	@Override
	public void extraccion(CuentaDto cuenta, Integer cantidad) {
		Integer saldo = cuenta.getSaldo();
		cuenta.setSaldo(saldo - cantidad);
		conversor.cuentaToDto(cuentasDao.save(conversor.dtoToCuenta(cuenta)));
		Calendar fechaNow = Calendar.getInstance();
		movimientoDao.save(new Movimiento(0, fechaNow.getTime(), cantidad, "extraccion de "+cuenta.getNumeroCuenta(),conversor.dtoToCuenta(cuenta)));
	}

	@Override
	public List<MovimientoDto> movimientosFechaBetween(Date first, Date second){
		return movimientoDao.findByMovimientoFechas(first, second)
				.stream().map(x -> conversor.movimientoToDto(x))
				.collect(Collectors.toList());
	}
	@Transactional
	@Override
	public boolean transferencia(CuentaDto cuenta1, Integer cantidad, CuentaDto cuenta2) {
		extraccion(cuenta1, cantidad);
		ingreso(cuenta2, cantidad);
		return true;
	}

	
	


}
