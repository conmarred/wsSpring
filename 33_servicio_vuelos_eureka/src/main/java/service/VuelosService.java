package service;

import java.util.List;

import model.Vuelo;

public interface VuelosService {
	List<Vuelo> vuelosDisponibles();
	List<Vuelo> disponibilidadVuelos(Integer plazas);
	boolean actualizarPlazas(Integer idVuelo, Integer plazas);
}
