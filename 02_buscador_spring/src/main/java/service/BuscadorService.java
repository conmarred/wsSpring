package service;

import java.util.List;


import model.Pagina;

public interface BuscadorService {
	
	List<Pagina> bucar(String tematica);
	void alta(Pagina pagina);
}
