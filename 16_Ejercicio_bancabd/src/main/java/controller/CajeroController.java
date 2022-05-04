package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CuentaDao;
import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;
import service.CajeroService;

@CrossOrigin("*")
@Controller
public class CajeroController {

	@Autowired
	CajeroService service;
	
	@PostMapping(value = "Login")
	public String login(@RequestParam("numeroCuenta") int numeroCuenta) {
		if(service.validarCuenta(numeroCuenta) == null) {
			return "errorLogin";
		}
		return "menu";
	}
	
	@PostMapping(value = "Ingreso")
	public String ingreso(@RequestParam("cantidad") Integer cantidad, HttpSession session) {
		service.ingreso((CuentaDto) session.getAttribute("numeroCuenta"), cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Extraccion")
	public String extraccion(@RequestParam("cantidad") Integer cantidad, HttpSession session) {
		service.extraccion((CuentaDto) session.getAttribute("numeroCuenta"), cantidad);
		return "menu";
	}
	
	@PostMapping(value = "Transferencia")
	public String transferecia(@RequestParam("cantidad") Integer cantidad, @RequestParam("cuenta2") Integer cuenta2, HttpSession session) {
		CuentaDto cuentaATransferir = service.validarCuenta((int) session.getAttribute("cuenta2"));
		service.transferencia((CuentaDto) session.getAttribute("numeroCuenta"), cantidad, cuentaATransferir);
		return "menu";
	}
	
	@GetMapping(value="MovimientosFechaBetween", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> movimientosFechaBetween(@RequestParam("first") @DateTimeFormat(pattern="yyyy-MM-dd") Date first, 
														@RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second){
		return service.movimientosFechaBetween(first, second);
	}

	
	
}
