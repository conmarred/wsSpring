package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="alumnos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//si aqui fuera por ej idAlumno, la mayus la interpreta como id_alumno
	//añadimos propiedades al applications.properties
	/*spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
	spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	*/
	private int id;
	private String nombre;
	private String curso;
	private double nota;
}
