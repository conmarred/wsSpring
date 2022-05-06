package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Pais;

@Service
class PaisServiceImpl implements PaisService {
	
	RestTemplate template;
	String urlBase="https://restcountries.com/v2/all";
	
	
	public PaisServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}

	@Override
	public List<String> continentes() {
		Pais[] paises = template.getForObject(urlBase, Pais[].class);
		return Arrays.stream(paises)
				.map(p->p.getContinente())
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	public List<Pais> paisPorContinente(String continente) {
		Pais[] paises = template.getForObject(urlBase, Pais[].class);
		return Arrays.stream(paises)
				.filter(p->p.getContinente().equals(continente))
				.collect(Collectors.toList());
	}

	@Override
	public Long poblacionContinente(String continente) {
		Pais[] paises = template.getForObject(urlBase, Pais[].class);
		List<Pais> paisesDelContinente = Arrays.stream(paises)
				.filter(p->p.getContinente().equals(continente))
				.collect(Collectors.toList());
		Long totalPoblacion = 0L;
		for(Pais p : paisesDelContinente) {
			Long poblacion = p.getPoblacion();
			totalPoblacion =+ poblacion;
		}
		return totalPoblacion;
	}

}
