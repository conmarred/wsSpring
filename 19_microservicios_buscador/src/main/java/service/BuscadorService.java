package service;

import java.util.List;


import model.Pagina;

public interface BuscadorService {
	
	List<Pagina> bucar(String tematica);
	void alta(Pagina pagina);
	List<Pagina> paginas();
	//eliminar todas las paginas asociadas a dicha tematica
	void eliminar(String tematica);
	Pagina actualizar(Pagina pagina);
	void actualizarTematica(String direccion, String tematica);
}
