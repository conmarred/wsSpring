package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Alumno {
	@Id
	private String usuario;
	private String password;
	private String nombre;
	private Integer edad;
	private String email;
	
	@OneToMany(mappedBy = "alumno")
	private List<Matricula> matriculas;

	public Alumno(String usuario, String password, String nombre, Integer edad, String email) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
	}

}
