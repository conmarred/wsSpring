package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Pagina;
import service.BuscadorService;

//indicates that a particular class serves the role of a controller. 
//Spring Controller annotation is typically used in combination with 
//annotated handler methods based on the @RequestMapping annotation. 
//It can be applied to classes only.
@Controller
public class BuscadorController {
	
	@Autowired
	BuscadorService buscadorService;
	
	@GetMapping(value="Buscador")
	public String buscar(@RequestParam("tematica") String tematica, HttpServletRequest request) {
		List<Pagina> paginas= buscadorService.bucar(tematica);
		request.setAttribute("paginas", paginas);
		return "listado";
	}
	
/*
	@PostMapping(value="NuevaPagina")
	public String nuevaPagina(@RequestParam("direccion") String direccion, 
			@RequestParam("tematica") String tematica, 
			@RequestParam("descripcion") String descripcion) {
		
		Pagina p1 = new Pagina(direccion, tematica, descripcion);
		buscadorService.alta(p1);
		
		return "datos";//DONDE QUEREMOS IR DESPUES DE COMPLETAR EL TRABAJO DEL CONTROLADOR
	}

*/
	
	//Otra forma de hacerlo:
	//Cuando recogemos varios parámetros y lo volcamos en un objeto
	//Spring lo hace solo de la siguiente manera:
	
	@PostMapping(value="NuevaPagina")
	public String nuevaPagina(@ModelAttribute("pagina") Pagina pagina) {
		
		Pagina p1 = pagina;
		buscadorService.alta(p1);
		
		return "datos";
	}
	
}
