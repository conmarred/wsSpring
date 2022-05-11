package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Pais;
import service.PaisService;
@CrossOrigin("*")
@RestController
public class PaisController {
	
	@Autowired
	PaisService service;
	
	@ApiOperation(value = "Devuelve una lista de todos los continentes")
	@GetMapping(value="Paises/Continentes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes(){
		return service.continentes();
	}
	
	@ApiOperation(value = "Devuelve una lista de los paises del continente cuyo nombre se recibe en la url")
	@GetMapping(value="Paises/{continente}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> paisPorContinente(@ApiParam(value = "Nombre del continente a buscar")@PathVariable("continente") String continente){
		return service.paisPorContinente(continente);
	}
	
	@ApiOperation(value = "Devuelve el valor de la poblacion del continente cuyo nombre se recibe en la url")
	@GetMapping(value="Pais/{continente}",produces=MediaType.TEXT_PLAIN_VALUE)
	public String poblacion(@ApiParam(value = "Nombre del continente a buscar")@PathVariable("continente") String continente){
		return String.valueOf(service.poblacionContinente(continente));
	}

}
