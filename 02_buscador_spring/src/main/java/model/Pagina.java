package model;

import org.springframework.web.bind.annotation.ModelAttribute;

//esto es un JavaBean 
public class Pagina {
	private String direccion;
	private String tematica;
	private String descripcion;
	
	public Pagina(String direccion, String tematica, String descripcion) {
		super();
		this.direccion = direccion;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}
	
	//Debe haber un constructor sin parámetros para usar el @ModelAttribute
	public Pagina() {
		
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
