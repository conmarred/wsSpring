package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dtos.UserPwdDto;
import model.Student;
@Service
public class StudentServiceImpl implements StudentService {

	//para comunicarme con el esterior necesito un restTemplate
	RestTemplate template;
	String urlBase="http://localhost:8000/crud";
	
	@Value("${user}")
	String user;
	@Value("${pwd}")
	String pwd;
	
	//variable token
	String token;
	
	
	//no es necesario poner autowired para que lo inyecte pero recomendable
	public StudentServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}
	
	//aqui vamos a generar el token para meterlo en la variable token
	private void getToken() {
		//tipo inferido
		var dto = new UserPwdDto(user, pwd);
		token= template.postForObject(urlBase+"/login", dto, String.class);
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		return headers;
	}

	@Override
	public void altaStudent(Student student) {
		if(token==null||token.equals("")) {
			getToken();
		}
		//construimos el encabezado Authorization en el token
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		//mandar el encabezado con el token en la llamada
		template.exchange(urlBase+"/Alumno", HttpMethod.POST, new HttpEntity<>(student,getHeaders()),Void.class);
		
	}

	@Override
	public List<Student> studentPorPuntuacionMinima(double puntuacion) {
		if(token==null||token.equals("")) {
			getToken();
		}
		
		
		Student[] estudiantes=template.exchange(urlBase+"/Alumnos", 
										HttpMethod.GET, 
										new HttpEntity<>(getHeaders()),
										Student[].class).getBody();
		//utilizamos streams para filtrar y transformar en lista
		return Arrays.stream(estudiantes) //stream
		.filter(s->s.getPuntuacion()>puntuacion) //filtramos por puntuaci??n
		.collect(Collectors.toList()); //List<Student>
	}

}
