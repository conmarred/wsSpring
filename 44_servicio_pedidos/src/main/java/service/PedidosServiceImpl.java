package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.PedidosDao;
import model.Pedido;
@Service
public class PedidosServiceImpl implements PedidosService {
	
	RestTemplate template;
	String urlBase="http://servicio-productos/";
	
	PedidosDao pedidosDao;

	public PedidosServiceImpl(@Autowired RestTemplate template, @Autowired PedidosDao pedidosDao) {
		super();
		this.template = template;
		this.pedidosDao = pedidosDao;
	}

	@Override
	public void altaPedido(Pedido pedido) {
		ResponseEntity<String> response = template.exchange(urlBase+"Producto/{codigoProducto}/{unidades}",
				HttpMethod.PUT,
				null, 
				String.class,
				pedido.getCodigoProducto(),
				pedido.getUnidades());
		String cuerpo = response.getBody();
		if(cuerpo.equals("true")) {
			pedidosDao.save(pedido);
		}
	}

	@Override
	public List<Pedido> pedidosRegistrados() {
		return pedidosDao.findAll();
	}

}
