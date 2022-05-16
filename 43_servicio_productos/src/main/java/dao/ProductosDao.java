package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Producto;

public interface ProductosDao extends JpaRepository<Producto, Integer> {

}
