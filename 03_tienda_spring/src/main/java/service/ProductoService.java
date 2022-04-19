package service;

import java.util.List;

import model.Producto;

public interface ProductoService {

	void alta(Producto producto);
	
	void eliminar(String nombre);
	
	List<Producto> listar(String seccion);
	
	void modificar(String nombre, Double precio);
}
