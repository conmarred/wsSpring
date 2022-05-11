package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Reserva;
import service.ReservaService;

@RestController
@CrossOrigin("*")
public class ReservaController {
	
	@Autowired
	ReservaService reservasService;
	
	//http://localhost:8500/servicioReserva/Reservas
	@GetMapping(value = "Reservas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Reserva> totalReservas() {
		return reservasService.totalReservas();
	}
	
	@PostMapping(value = "Reservar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addReserva(@RequestBody Reserva reserva) {
		reservasService.reservar(reserva);
	}
	
	//http://localhost:8500/servicioReserva/Reserva/3
	@PostMapping(value="Reserva/{plazas}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void altaReserva(@RequestBody Reserva reserva,@PathVariable("plazas") int plazas) {
		reservasService.reservarPlazasDispo(reserva, plazas);
	}

}
