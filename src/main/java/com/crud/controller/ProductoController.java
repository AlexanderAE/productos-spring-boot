package com.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.model.Producto;
import com.crud.repository.ProductoRepository;

@Controller
@RequestMapping("/productos")//http:localhost:8080/productos
public class ProductoController {
	
	private final Logger logg = LoggerFactory.getLogger(Producto.class);
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping("")
	public String home(Model model){
		
		model.addAttribute("productos", productoRepository.findAll());
		
		return "home";
	}
	
	@GetMapping("/createe")//http://localhost:8080/productos/createe
	public String create() {
		return "createe";
	}
	
	@PostMapping("/save")
	public String save(Producto producto) {
		logg.info("Informacion del Objeto Producto,{}", producto);
		productoRepository.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model ){
		Producto p= productoRepository.getOne(id);
		logg.info("Objeto recuperado {}",p);
		model.addAttribute("producto", p);
		return "edit";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id ){
		Producto p= productoRepository.getOne(id);
		logg.info("Objeto eliminado {}",p);
		productoRepository.delete(p);
		return "redirect:/productos";
	}
	

}
