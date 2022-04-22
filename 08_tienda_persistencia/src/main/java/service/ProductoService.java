package service;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import model.Producto;

public interface ProductoService {

	void alta(Producto producto);
	
	void eliminar(String nombre);
	
	List<Producto> listar(String seccion);
	
	void modificar(String nombre, Double precio);
	
	Producto buscarProductoId(int id);
	
	Producto buscarProducto(String nombre);

}
