package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	@Transactional
	@Override
	public void eliminar(String nombre) {
		TypedQuery<Producto> query=entityManager.createNamedQuery("Producto.findByNombre", Producto.class);
		query.setParameter("nombre", nombre);
		List<Producto> lsProducto = query.getResultList();
		Producto aux = lsProducto.size()>0? lsProducto.get(0) : null;
		entityManager.remove(aux);
		}

	@Override
	public List<Producto> listar(String seccion) {
		TypedQuery<Producto> query=entityManager.createNamedQuery("Producto.findBySeccion", Producto.class);
		query.setParameter("seccion", seccion);
		List<Producto> resultado=query.getResultList();
		return resultado;
	}

	@Transactional
	@Override
	public void modificar(String nombre, Double precio) {
		String jpql="update Producto p set p.precio=:precio where p.nombre=:nombre";
		Query query =entityManager.createQuery(jpql);
		query.setParameter("precio", precio);
		query.setParameter("nombre", nombre);
		query.executeUpdate();
		
	}
	
	@Override
	public Producto buscarProductoId(int id) {
		return entityManager.find(Producto.class, id);
	}
	
	@Override
	public Producto buscarProducto(String nombre) {
		return entityManager.find(Producto.class, nombre);
	}

	


}
