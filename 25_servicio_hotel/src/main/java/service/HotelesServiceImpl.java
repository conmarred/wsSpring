package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HotelesDao;
import model.Hotel;
@Service
public class HotelesServiceImpl implements HotelesService {

	HotelesDao hotelesDao;
	
	
	public HotelesServiceImpl(@Autowired HotelesDao hotelesDao) {
		super();
		this.hotelesDao = hotelesDao;
	}


	@Override
	public List<Hotel> hotelesDisponibles() {
		List<Hotel> aux = hotelesDao.findAll()
				.stream()
				.filter(p->p.getDisponible()==1)
				.collect(Collectors.toList());
		return aux;
	}


	@Override
	public Hotel buscarHotel(String nombre) {
		Hotel hotel = hotelesDao.findByNombre(nombre);
		return hotel;
	}
	
	

}
