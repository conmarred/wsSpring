package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Movimiento;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer>{
	//findByFechaBetween
	//si lo pongo de esta forma no hace falta poner la query
	List<Movimiento> findByCuentaNumeroCuentaAndFechaBetween(int idCuenta, Date inicio, Date fin);

	@Query("select m from Movimiento m where m.fecha between ?1 and ?2")
	List<Movimiento> findByMovimientoFechas(Date first, Date second);
}
