package service;

import java.util.List;

import model.Pedido;

public interface PedidosService {
	/**
	 * @author concepcion.marquez
	 * Un recurso que dará de alta un nuevo pedido al recibir en una
	 * petición de tipo post , que incluirá en el cuerpo de la misma los
	 * datos del pedido. Desde este servicio se realizarán las
	 * correspondientes llamadas a los recursos del servicio de
	 * productos para actualizar el stock.
	 * @param pedido
	 */
	
	void altaPedido(Pedido pedido);
	
	/**
	 * @author concepcion.marquez
	 * Un recurso que al recibir una petición get devuelva todos los
	 * pedidos registrados
	 * @return Lista de todos los pedidos
	 */
	List<Pedido> pedidosRegistrados();

}
