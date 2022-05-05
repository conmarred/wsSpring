package service;

import java.util.List;
import model.Producto;

public interface ProductoService {
	/*Crear un microservicio de productos, que accediendo a la base de datos de tienda, exponga 
	los siguientes recursos:
	- Alta de productos a partir de los datos recibidos en el cuerpo
	- Eliminación de producto por su id
	- Recuperación de todos los productos
	- Recuperación de productos por sección
	*/

	void alta(Producto producto);
	boolean eliminar(int id);
	List<Producto> listarProductos();
	List<Producto> listarProductosSeccion(String seccion);
	Producto buscarProducto(int id);

}
