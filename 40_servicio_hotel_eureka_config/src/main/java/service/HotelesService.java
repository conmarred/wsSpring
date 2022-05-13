package service;

import java.util.List;

import model.Hotel;

public interface HotelesService {
	List<Hotel> hotelesDisponibles();	
	public Hotel buscarHotel(String nombre);
}
