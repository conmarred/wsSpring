package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Vuelo;
import service.VuelosService;

@RestController
@CrossOrigin("*")
public class VuelosController {
	
	@Autowired
	VuelosService vuelosService;
	
	@GetMapping(value = "Vuelos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> disponibilidadVuelos(){
		List<Vuelo> aux = vuelosService.vuelosDisponibles();
		return aux;
	}
	
	@GetMapping(value = "Vuelos/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> disponibilidadVuelos(@PathVariable("plazas") Integer plazas){
		List<Vuelo> aux = vuelosService.disponibilidadVuelos(plazas);
		return aux;
	}
	
	//http://localhost:9000/servicioVuelo/Vuelo?idVuelo=1&plazas=20
	@PutMapping(value = "Vuelo", produces = MediaType.TEXT_PLAIN_VALUE)
	public String actualizarPlazas(@RequestParam("idVuelo") int idVuelo, @RequestParam("plazas") Integer plazas) {
		return String.valueOf(vuelosService.actualizarPlazas(idVuelo, plazas));
	}
	
	@PutMapping(value = "Vuelo/{idVuelo}/{plazas}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String modificarVuelo(@PathVariable("idvuelo") int idvuelo, @PathVariable("plazas") Integer plazas) {
		return String.valueOf(vuelosService.actualizarPlazas(idvuelo, plazas));
	}

}
