package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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
@Table(name="productos")
@NamedQuery(name = "Producto.findBySeccion", query="select p from Producto p where p.seccion = :seccion")
@NamedQuery(name = "Producto.findByNombre", query="select p from Producto p where p.nombre = :nombre")
@NamedQuery(name = "Producto.updateByNombre", query="update Producto set precio = :precio  where nombre= :nombre")
public class Producto {
	@Id
	//para que el framework sepa que es una PK autogenerada
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	@Column(name="seccion") //asociado a la columna.. x si cambio el nombre del atributo
	private String seccion;
	private double precio;
	private int stock;
}
