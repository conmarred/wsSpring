package service;

import java.util.List;

import model.Producto;

public interface ProductosService {
	/**
	 * @author concepcion.marquez
	 * Un recurso que ante una petición get, devuelve la lista de
	 * productos existentes.
	 * @return Lista de productos
	 */
	List<Producto> listaProductos();
	
	/**
	 * @author concepcion.marquez
	 * Un recurso que al recibir una petición put, actualiza el stock
	 * del producto indicado. En la URL de la petición se reciben el
	 * del producto indicado. En la URL de la petición se reciben el
	 * código de producto y unidades compradas
	 * @param codigoProducto
	 * @param unidades
	 * @return true -> si la actualizacion es correcta, false ecc.
	 */
	boolean actualizaStock(Integer codigoProducto, Integer unidades);
	
	/**
	 * @author concepcion.marquez
	 * Un recurso que al recibir una petición get con el código del
	 * producto devuelva el precio unitario del mismo
	 * @param codigoProducto
	 * @return double -> precio unitario del producto
	 */
	double precioUnitario(Integer codigoProducto);
	
}
