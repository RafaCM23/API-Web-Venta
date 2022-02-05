package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;

@SpringBootApplication
public class ApiWebventaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWebventaApplication.class, args);
	}

	
	@Bean
	CommandLineRunner iniProductos (ProductoRepository productoREPO) {
		return (args) -> {
			productoREPO.saveAll(Arrays.asList(
					new Producto(1,"Steam Gift Card 25€"),
					new Producto(2,"Amazon Gift Card 25€"),
					 new Producto(3,"Google Play Gift Card 25€"),
					 new Producto(4,"Xbox  Gift Card 25€"),
					 new Producto(5,"eShop Gift Card 25€"),
					 new Producto(6,"PS Store Gift Card 25€")));
		};
	}
	
	@Bean
	CommandLineRunner iniUsuarios (UsuarioRepository userREPO) {
		return (args) -> {
			userREPO.saveAll(Arrays.asList(
					new Usuario(1,"rafa","contra","micasa",9665566,"surfearr2@gmail.com"),
					new Usuario(2,"admin","admin","anonymous",000000000,"anon@anon.es"),
					new Usuario(3,"pepe","pepe23","C/ Felipe II",123456789,"pepePP@hotmail.org")));
		};
	}
}
