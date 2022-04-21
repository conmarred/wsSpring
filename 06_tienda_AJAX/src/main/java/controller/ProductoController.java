package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Producto;
import service.ProductoService;
@CrossOrigin("*")
@Controller
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@PostMapping(value="Alta")
	public String alta(@ModelAttribute("producto") Producto producto) {
		productoService.alta(producto);
		return "inicio";
	}
	
	@GetMapping(value="Eliminar")
	public String eliminar(@RequestParam("nombre") String nombre) {
		productoService.eliminar(nombre);
		return "inicio";
	}
	
	@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Producto> buscar(@RequestParam("seccion") String seccion) {
		return productoService.listar(seccion);
	}
	
	//Se puede omitir value si solo es un atributo
	//@GetMapping("Modificar")
	@GetMapping(value="Modificar")
	public String modificar(@RequestParam("nombre") String nombre, @RequestParam("precio") Double precio) {
		productoService.modificar(nombre, precio);
		return "inicio";
	}
	
}
