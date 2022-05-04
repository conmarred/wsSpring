package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Cuenta;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovimientoDto {
	private int idMovimiento;
	private CuentaDto idCuenta;
	private Date fecha;
	private int cantidad;
	private String operacion;
}
