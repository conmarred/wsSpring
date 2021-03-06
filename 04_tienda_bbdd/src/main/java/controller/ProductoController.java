package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Producto;
import service.ProductoService;

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
	
	@GetMapping(value="Buscador")
	public String buscar(@RequestParam("seccion") String seccion, HttpServletRequest request) {
		List<Producto> productos= productoService.listar(seccion);
		request.setAttribute("productos", productos);
		return "listado";
	}
	
	@GetMapping(value="Modificar")
	public String modificar(@RequestParam("nombre") String nombre, @RequestParam("precio") Double precio) {
		productoService.modificar(nombre, precio);
		return "inicio";
	}
	
}
