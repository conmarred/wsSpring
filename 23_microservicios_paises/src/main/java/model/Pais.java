package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
/*Creación del microservicio de paises
Se trata de crar un microservicio que interaccione con el servicio remoto de paises
Definiremos un modelo de datos Pais con los siguientes atributos: nombre,capital,continente,poblacion y bandera
Recursos:
- Lista de continentes
- Paises por continente. Recibe una petición get con el nombre de un continente, y devuelve los paises
del mismo
- Población continente. Recibe una peticion get con el nombre de un continente, y devuelve el total de
habitantes de dicho continente
*/
public class Pais {
	@JsonAlias(value="name")
	private String nombre;
	private String capital;
	@JsonAlias(value="region")
	private String continente;
	@JsonAlias(value="population")
	private Long poblacion;
	@JsonAlias(value="flag")
	private String bandera;

}
