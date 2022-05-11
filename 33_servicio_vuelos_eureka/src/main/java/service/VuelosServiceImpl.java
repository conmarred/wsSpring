package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VuelosDao;
import model.Vuelo;
@Service
public class VuelosServiceImpl implements VuelosService {
	
	VuelosDao vuelosDao;
	
	
	public VuelosServiceImpl(@Autowired VuelosDao vuelosDao) {
		super();
		this.vuelosDao = vuelosDao;
	}


	@Override
	public List<Vuelo> vuelosDisponibles() {
		List<Vuelo> aux =  vuelosDao.findAll()
				.stream()
				.filter(v->v.getPlazas()>0)
				.collect(Collectors.toList());
		return aux;
	}


	@Override
	public boolean actualizarPlazas(Integer idVuelo, Integer plazas) {
		boolean existeVuelo = vuelosDao.findAll().stream().anyMatch(v->v.getIdvuelo()==idVuelo);
		if(!existeVuelo) {
			return false;
		}else {
			Vuelo vueloAux = vuelosDao.findById(idVuelo).get();
			vueloAux.setPlazas(vueloAux.getPlazas() - plazas);
			vuelosDao.save(vueloAux);
			return true;
		}
		
	}


	@Override
	public List<Vuelo> disponibilidadVuelos(Integer plazas) {
		List<Vuelo> aux =  vuelosDao.findAll()
				.stream()
				.filter(v->v.getPlazas()>=plazas)
				.collect(Collectors.toList());
		return aux;
	}

}
