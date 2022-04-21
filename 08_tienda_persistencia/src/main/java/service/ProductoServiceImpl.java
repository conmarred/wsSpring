package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	@Override
	public void alta(Producto producto) {
		entityManager.persist(producto);
	}
	@Override
	public void eliminar(String nombre) {
		
	}

	@Override
	public List<Producto> listar(String seccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificar(String nombre, Double precio) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Producto buscarProductoId(int id) {
		return entityManager.find(Producto.class, id);
	}

	


}
