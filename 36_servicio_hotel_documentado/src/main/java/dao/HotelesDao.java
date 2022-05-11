package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Hotel;

	
public interface HotelesDao extends JpaRepository<Hotel, Integer> {
	Hotel findByNombre(String nombre);
}
