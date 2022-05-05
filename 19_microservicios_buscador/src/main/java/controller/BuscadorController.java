package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Pagina;
import service.BuscadorService;

//con esto no necesito responsebody la respuesta de los metodos se entienden por el contenido 
//que hay en enviar en el body
@RestController
public class BuscadorController {
	@Autowired
	BuscadorService service;
	@GetMapping(value="Paginas",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina> paginas(){
		return service.paginas();
	}
	/*@GetMapping(value="Buscador",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina> buscador(@RequestParam("tematica") String tematica){
		return service.bucar(tematica);
	}*/
	@GetMapping(value="Paginas/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
	//@PathVariable para que me vuelque la variable
	public List<Pagina> buscador(@PathVariable("tematica") String tematica){
		return service.bucar(tematica);
	}
	@PostMapping(value="Pagina",consumes=MediaType.APPLICATION_JSON_VALUE)
	//lo que venga en el body de la peticion vuelcamelo en pagina
	public void alta(@RequestBody Pagina pagina) {
		service.alta(pagina);
	}
	@DeleteMapping(value="Pagina")
	public void eliminar(@RequestParam("tematica") String tematica) {
		service.eliminar(tematica);
	}
	//consume o produce un json
	@PutMapping(value="Pagina",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Pagina actulizar(@RequestBody Pagina pagina) {
		return service.actualizar(pagina);
	}
	@PutMapping(value="Pagina")
	public void actualizarTematica(@RequestParam("direccion") String direccion, @RequestParam("tematica") String tematica) {
		service.actualizarTematica(direccion, tematica);
	}
}
