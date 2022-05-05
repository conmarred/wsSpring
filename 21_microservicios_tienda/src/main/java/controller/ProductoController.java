package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Producto;
import service.ProductoService;
@RestController
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@PostMapping(value = "Producto", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void altaAlumno(@RequestBody Producto producto) {
		productoService.alta(producto);
	}
	
	@DeleteMapping(value = "Producto/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String eliminarAlumno(@PathVariable("id") int id) {
		return  String.valueOf(productoService.eliminar(id));
	}
	
	@GetMapping(value = "Productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> listarProductos() {
		return productoService.listarProductos();
	}
	
	@GetMapping(value = "Productos/{seccion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> listarProductosSeccion(@PathVariable("seccion") String seccion) {
		return productoService.listarProductosSeccion(seccion);
	}
	
}
