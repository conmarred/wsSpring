package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ReservasDao;
import model.Reserva;
@Service
public class ReservaServiceImpl implements ReservaService {
	//para comunicarme con el esterior necesito un restTemplate
	RestTemplate template;
	String urlBase="http://servicio_vuelo/servicioVuelo/";
		
		
	ReservasDao reservasDao;
	
	public ReservaServiceImpl(@Autowired ReservasDao reservasDao, @Autowired RestTemplate template) {
		super();
		this.reservasDao = reservasDao;
		this.template = template;
	}
	
	@Override
	public boolean reservar(Reserva reserva) {
			reservasDao.save(reserva);
			return true;
	}

	@Override
	public List<Reserva> totalReservas() {
		List<Reserva> reservas = reservasDao.findAll();
		return reservas;
	}

	@Override
	public void reservarPlazasDispo(Reserva reserva, Integer plazas) {
		ResponseEntity<String> response = template.exchange(urlBase+"Vuelo/{idVuelo}/{plazas}",
				HttpMethod.PUT,
				null, // aqui hubiera diso new HttpEntity(dato_cuerpo)
				String.class,
				reserva.getVuelo(),
				plazas);
		//solo guardamos la reserva si se ha actualizado el número de plazas de los vuelos
		String cuerpo = response.getBody();
		if(cuerpo.equals("true")) {
			reservasDao.save(reserva);
		}
	}


}
