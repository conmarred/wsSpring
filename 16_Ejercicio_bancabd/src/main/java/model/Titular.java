package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "titulares")
public class Titular {
	@EmbeddedId
	private TitularPK id;
	
//	@ManyToOne()
//	@JoinColumn(name = "idCuenta", referencedColumnName = "idCuenta", insertable = false, updatable = false)
//	private Cuenta cuenta;
//	
//	@ManyToOne()
//	@JoinColumn(name = "idCliente", referencedColumnName = "idCliente", insertable = false, updatable = false)
//	private Cliente cliente;
}
