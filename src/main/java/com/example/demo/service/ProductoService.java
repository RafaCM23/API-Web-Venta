package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {

@Autowired
ProductoRepository productoREPO;

	public List<Producto> findAll(){
		return this.productoREPO.findAll();
		}
	
	public int guardaProducto(Producto p) {
		if(p==null) {
			return -1;
		}
		else {
			this.productoREPO.save(p);
			return 1;
		}
		
	}
	
	public Producto getById(int id) {
		return this.productoREPO.findById(id).orElse(null);
	}
}
