package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//equals y hashCode
@EqualsAndHashCode
@Embeddable

//Clase de la PK
//debe ser serializable (un objeto se puede traducir a 1 y 0 y de ahi reconstruir el objeto)
public class MatriculaPK implements Serializable{
	private String usuario;
	private Integer idCurso;
	
	
}
