package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ProductosDao;
import model.Producto;
@Service
public class ProductosServiceImpl implements ProductosService {
	RestTemplate template;
	
	ProductosDao productosDao;
	
	
	public ProductosServiceImpl(@Autowired RestTemplate template, @Autowired ProductosDao productosDao) {
		super();
		this.template = template;
		this.productosDao = productosDao;
	}

	@Override
	public List<Producto> listaProductos() {
		List<Producto> productos = productosDao.findAll();
		return productos;
	}

	@Override
	public double precioUnitario(Integer codigoProducto) {
		Producto aux = productosDao.findById(codigoProducto).orElse(null);
		if(aux!=null) {
			return aux.getPrecioUnitario();
		}else {
			return 0;
		}
	}

	@Override
	public boolean actualizaStock(Integer codigoProducto, Integer unidades) {
		Producto aux = productosDao.findById(codigoProducto).orElse(null);
		if(aux!=null && aux.getStock()>= unidades) {
			aux.setStock(aux.getStock()-unidades);
			productosDao.save(aux);
			return true;
		}else {
			return false;
		}
	}

}
