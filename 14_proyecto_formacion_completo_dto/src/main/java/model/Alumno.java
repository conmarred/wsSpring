package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	//@JsonIgnore
	@ManyToMany()
	@JoinTable(name="matriculas",
		joinColumns = @JoinColumn(name="usuario",referencedColumnName = "usuario"), 
        inverseJoinColumns = @JoinColumn(name="idCurso", referencedColumnName ="idCurso"))
	private List<Curso> cursos;

	public Alumno(String usuario, String password, String nombre, Integer edad, String email) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
	}

	
	
}
