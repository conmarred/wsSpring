package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional //spring comprueba si hay una transaccion abierta, si no, la inicia
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
		
		//Otra forma de hacerlo:		
		
//		String jpql ="delete from Producto p where p.nombre = :nombre";
//		Query query = entityManager.createQuery(jpql);
//		query.setParameter("nombre", nombre);
//		query.executeUpdate();
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
//		String jpql="update Producto p set p.precio=:precio where p.nombre=:nombre";
//		Query query =entityManager.createQuery(jpql);
//		query.setParameter("precio", precio);
//		query.setParameter("nombre", nombre);
//		query.executeUpdate();
		
		//Otra forma de hacerlo:
		
		Producto producto = buscarProducto(nombre);
		if(producto!=null) {
			producto.setPrecio(precio);
			entityManager.merge(producto); //actualizamos el producto
		}
		
	}
	
	@Override
	public Producto buscarProductoId(int id) {
		//Busca por primary key
		return entityManager.find(Producto.class, id);
	}
	
	@Override
	public Producto buscarProducto(String nombre) {
		String jpql ="select p from Producto p where p.nombre = :nombre";
		TypedQuery<Producto> query=entityManager.createNamedQuery(jpql, Producto.class);
		query.setParameter("nombre", nombre);
		List<Producto> productos = query.getResultList();
		return productos.size()>0?productos.get(0):null;
	}

	@Override
	public double PrecioMedioSeccion(String seccion) {
		String jpql ="select avg(p.precio) from Producto p where p.seccion = :seccion";
		TypedQuery<Double> query=entityManager.createQuery(jpql, Double.class);
		query.setParameter("seccion", seccion);
		return query.getSingleResult(); //me devuelve un unico valor
	}

}
