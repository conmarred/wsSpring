package dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//anotaciones lombok si porque son javabean
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CursoDto {
	//aqui no hay notaciones de persistencia, son clases neutras
	private Integer idCurso;
	private String nombre;
	private Integer duracion;
	private Double precio;
	private Date fechaInicio;
	//quito de aqui la lista de alumnos ya que ahora tengo un MatriculaDto
//	private List<String> alumnos;

}
