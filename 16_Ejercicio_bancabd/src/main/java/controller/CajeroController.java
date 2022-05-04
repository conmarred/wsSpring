package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.CuentaDto;
import dto.MovimientoDto;
import service.CajeroService;

@CrossOrigin("*")
@Controller
public class CajeroController {

	@Autowired
	CajeroService service;
	
	private CuentaDto cuentaSesion;
	
	@PostMapping(value = "Login")
	public String login(@RequestParam("numeroCuenta") int numeroCuenta) {
		CuentaDto cuentaValida = service.validarCuenta(numeroCuenta);
		if(service.validarCuenta(numeroCuenta) == null) {
			return "errorLogin";
		}else {
			cuentaSesion=cuentaValida;
			return "menu";
		}
		
	}
	
	@PostMapping(value = "Ingreso")
	public String ingreso(@RequestParam("cantidad") Integer cantidad) {
		service.ingreso(cuentaSesion, cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Extraccion")
	public String extraccion(@RequestParam("cantidad") Integer cantidad) {
		service.extraccion(cuentaSesion, cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Transferencia")
	public String transferecia(@RequestParam("cantidad") Integer cantidad, @RequestParam("cuenta2") int cuenta2) {
		CuentaDto cuentaValida2 = service.validarCuenta(cuenta2);
		if(cuentaValida2==null) {
			return "errorLogin";
		}else {
			service.transferencia(cuentaSesion, cantidad, cuentaValida2);
			return "menu";
		}
		
	}
	//http://localhost:8080/16_Ejercicio_bancabd/MovimientosFechaBetween?first=2019-03-31&second=2019-04-01
	@GetMapping(value="MovimientosFechaBetween", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> movimientosFechaBetween(@RequestParam("first") @DateTimeFormat(pattern="yyyy-MM-dd") Date first, 
														@RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second){
		return service.movimientosFechaBetween(cuentaSesion.getNumeroCuenta(),first, second);
	}

	
	
}
