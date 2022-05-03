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

import dto.ClienteDto;
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
	
	@GetMapping(value = "MovimientosFechaBetween")
	public @ResponseBody List<MovimientoDto> buscarMovimientos(@RequestParam("first") @DateTimeFormat(pattern = "yyyy-MM-dd") Date first, 
																@RequestParam("second") @DateTimeFormat(pattern = "yyyy-MM-dd") Date second){
		return service.movimientosFechaBetween(first, second);
	}
	
	@GetMapping(value = "TodosClientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ClienteDto> todosClientes(){
		return service.listaClientes();
	}
	
	
}
