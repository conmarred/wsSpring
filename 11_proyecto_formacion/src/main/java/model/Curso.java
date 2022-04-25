package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCurso;
	private String nombre;
	private Integer duracion;
	private Double precio;
	//Recomendable utilizar temporal para saber como tratar la fecha
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@ManyToMany()
	@JoinTable(name="matriculas",
		joinColumns = @JoinColumn(name="usuario",referencedColumnName = "usuario"), 
        inverseJoinColumns = @JoinColumn(name="idCurso", referencedColumnName ="idCurso"))
	private List<Curso> cursos;
	
}
