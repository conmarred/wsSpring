package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Hotel;
import service.HotelesService;

@RestController
public class HotelController {
	
	@Autowired
	HotelesService hotelesService;
	//http://localhost:8000/servicioHotel/Hoteles
	@GetMapping(value = "Hoteles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> hotelesDisponibles(){
		return hotelesService.hotelesDisponibles();
	}
	//http://localhost:8000/servicioHotel/Hotel?nombre=Norte
	@GetMapping(value = "Hotel/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hotel buscarHotel(@PathVariable("nombre") String nombre){
		return hotelesService.buscarHotel(nombre);
	}
	

}
