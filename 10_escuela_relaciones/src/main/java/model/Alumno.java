package model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="alumnos")
public class Alumno {
	@Id
	private String dni;
	private String nombre;
	private Integer edad;
	private String email;
	
	//Sustituimos FK por el objeto curso
	@ManyToOne()
    @JoinColumn(name="curso", referencedColumnName = "idCurso")
	private Curso curso;
}
