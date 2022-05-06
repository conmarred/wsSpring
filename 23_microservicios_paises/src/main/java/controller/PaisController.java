package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.PaisService;
@CrossOrigin("*")
@RestController
public class PaisController {
	
	@Autowired
	PaisService service;
	
	@GetMapping(value="Paises/Continentes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes(){
		return service.continentes();
	}
	
	@GetMapping(value="Paises/{continente}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> paisPorContinente(@PathVariable("continente") String continente){
		return service.paisPorContinente(continente);
	}
	
	@GetMapping(value="Pais/{continente}",produces=MediaType.TEXT_PLAIN_VALUE)
	public String poblacion(@PathVariable("continente") String continente){
		return String.valueOf(service.poblacionContinente(continente));
	}

}
