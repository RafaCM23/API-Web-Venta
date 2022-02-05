package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;

@Service
public class LineaPedidoService {

	@Autowired
	LineaPedidoRepository lineaPedidoREPO;
	
	@Autowired 
	ProductoRepository productoREPO;
	
	@Autowired
	PedidoRepository pedidoREPO;
	
	
	
	@Autowired
	
	public List<LineaPedido> findAll(){
		return lineaPedidoREPO.findAll();
	}
	
	public List<LineaPedido> findByPedido(Pedido p){
		return lineaPedidoREPO.findByPedido(p);
	}
	public LineaPedido creaLinea(Pedido p, LineaPedido linea) {
		Producto pro= productoREPO.findById(linea.getId_producto()).orElse(null);
		LineaPedido nueva = new LineaPedido(p,pro,linea.getCantidad());
		lineaPedidoREPO.save(nueva);
		return nueva;
	}
	
	public LineaPedido getById(int id) {
		return lineaPedidoREPO.findById(id).orElse(null);
	}
	public void delete(LineaPedido lp) {
		lineaPedidoREPO.delete(lp);
	}
	
	public void guardaLinea(LineaPedido lp) {
		lineaPedidoREPO.save(lp);
	}
	public LineaPedido sobreescribe(LineaPedido original,LineaPedido nueva) {
		original.cantidad=nueva.cantidad;
		lineaPedidoREPO.save(original);
		return original;
	}
}
