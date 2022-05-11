package service;

import java.util.List;

import model.Reserva;

public interface ReservaService {
	boolean reservar (Reserva reserva);
	void reservarPlazasDispo (Reserva reserva, Integer plazas);
	List<Reserva> totalReservas();
}
