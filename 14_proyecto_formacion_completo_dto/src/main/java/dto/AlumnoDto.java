package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AlumnoDto {
	private String usuario;
	private String password;
	private String nombre;
	private Integer edad;
	private String email;

}
