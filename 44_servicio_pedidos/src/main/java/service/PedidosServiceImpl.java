package service;

import java.sql.Timestamp;
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
		ResponseEntity<String> response = template.exchange(urlBase+"Producto/"+pedido.getCodigoProducto()+"/"+pedido.getUnidades(),
				HttpMethod.PUT,
				null, 
				String.class);
		String cuerpo = response.getBody();
		
		ResponseEntity<Double> response2 =template.exchange(urlBase+"Producto/"+pedido.getCodigoProducto(), 
				HttpMethod.GET,
				null,
				double.class);
		
		if(cuerpo.equals("true")) {
			Long date = System.currentTimeMillis();
			Timestamp time = new Timestamp(date);
			pedido.setFechaPedido(time);
			pedido.setTotal(response2.getBody()*pedido.getUnidades());
			pedidosDao.save(pedido);
		}
	}

	@Override
	public List<Pedido> pedidosRegistrados() {
		return pedidosDao.findAll();
	}

}
