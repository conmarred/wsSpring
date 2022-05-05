package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.ProductosDao;
import model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	ProductosDao productosDao;
	
	
	public ProductoServiceImpl(ProductosDao productosDao) {
		super();
		this.productosDao = productosDao;
	}

	@Override
	public void alta(Producto producto) {
		productosDao.save(producto);
		
	}

	@Override
	public boolean eliminar(int id) {
		if(buscarProducto(id)!=null) {
		productosDao.deleteById(id);
		return true;
		}
		return false;
	}

	@Override
	public List<Producto> listarProductos() {
		List<Producto> aux = productosDao.findAll();
		return aux;
	}

	@Override
	public List<Producto> listarProductosSeccion(String seccion) {
		List<Producto> aux = productosDao.findBySeccion(seccion);
		return aux;
	}

	@Override
	public Producto buscarProducto(int id) {
		Producto aux = productosDao.findById(id).orElse(null);
		return aux;
	}

	
	

}
