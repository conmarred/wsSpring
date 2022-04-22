package model;

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
@Table(name="alumnos")
@NamedQuery(name="Alumno.findAll", query="select a from Alumno a")
@NamedQuery(name="Alumno.findByNombre", query="select a from Alumno a where a.nombre = :nombre")
public class Alumno {
	@Id
	//para que el framework sepa que es una PK autogenerada
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String curso;
	private double nota;
}
