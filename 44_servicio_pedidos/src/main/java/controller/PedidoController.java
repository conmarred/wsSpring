package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Pedido;
import service.PedidosService;

@RestController
@CrossOrigin("*")
public class PedidoController {
	
	@Autowired
	PedidosService pedidosService;
	
	@PostMapping(value = "Pedido", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void altaPedido(@RequestBody Pedido pedido) {
		pedidosService.altaPedido(pedido);
	}
	
	@GetMapping(value = "Pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pedido> totalPedidos(){
		return pedidosService.pedidosRegistrados();
	}
}
