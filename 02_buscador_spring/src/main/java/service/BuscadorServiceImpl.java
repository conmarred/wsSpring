package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import model.Pagina;


//Anotaci�n de spring para que spring cree una instancia de la clase
//can be applied only to classes. 
//It is used to mark the class as a service provider.
@Service
public class BuscadorServiceImpl implements BuscadorService {
	
	static ArrayList<Pagina> paginas=new ArrayList<>();
	public BuscadorServiceImpl() {
		paginas.add(new Pagina("http://www.fnac.es","libros","Libros y m�s cosas"));
		paginas.add(new Pagina("http://www.gamer.es","juegos","Juegos on-line"));
		paginas.add(new Pagina("http://www.casadellibro.es","libros","La Web de los libros"));
		paginas.add(new Pagina("http://www.mydisc.es","musica","M�sica de todo tipo"));
		paginas.add(new Pagina("http://www.radio.es","musica","M�sica de actualidad"));
	}
	//bucamos las p�ginas cuya tem�tica coincida con la recibida
	//como par�metro
	public List<Pagina> buscar(String tematica){
		//en este arraylist auxiliar guardamos las p�ginas
		//cuya tem�tica coincida con la recibida como par�metro
		List<Pagina> auxiliar=new ArrayList<>();
		for(int i=0;i<paginas.size();i++) {
			Pagina pagina=paginas.get(i);
			if(pagina.getTematica().equals(tematica)) {
				auxiliar.add(pagina);
			}
		}
		return auxiliar;
	}
	
	@Override
	public List<Pagina> bucar(String tematica) {
		// TODO Auto-generated method stub
		return paginas
				.stream()
				.filter(p->p.getTematica().equals(tematica))
				.collect(Collectors.toList());
	}
	@Override
	public void alta(Pagina pagina) {
		paginas.add(pagina);	
	}

}
