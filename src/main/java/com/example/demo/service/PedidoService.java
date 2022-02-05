package com.example.demo.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cantidades;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;


@Service
public class PedidoService {
	
	
	@Autowired
	ProductoRepository productoREPO;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	LineaPedidoRepository lineaPedidoREPO;
	
	@Autowired
	PedidoRepository pedidoREPO;
	
	@Autowired
	LineaPedidoService lineaPedidoService;
	
	public List<Pedido> findAll(){
		return pedidoREPO.findAll();
	}
	
	public Pedido getById(int id) {
		return pedidoREPO.findById(id).orElse(null);
	}
	
	public Pedido creaVacio(Usuario user) {
		Pedido p = new Pedido(user,user.getDireccion(),user.getTlfn(),user.getCorreo());
		user.addPedido(p);
		pedidoREPO.save(p);
		usuarioService.guardaUsuario(user);
		return p;
	}
	public Pedido modificaInfo(Pedido p,Pedido aux) {
		p.setDireccion(aux.getDireccion());
		p.setTlfn(aux.getTlfn());
		p.setCorreo(aux.getCorreo());
		pedidoREPO.save(p);
		return p;
	}
	
	public Pedido addLinea(int idpedido,LineaPedido linea) {
		Pedido p = pedidoREPO.findById(idpedido).orElse(null);
		p.addLinea(linea);
		this.guardaPedido(p);
		return p;
	}
	
	public void guardaPedido(Pedido p) {
		pedidoREPO.save(p);
	}
	public void borraLinea(LineaPedido lp) {
		Pedido p=getById(lp.getId_pedido());
		p.borrarLinea(p.getLineas().indexOf(lp));
		guardaPedido(p);
		this.lineaPedidoREPO.delete(lp);

	}
	
			
	public int borraPedido(int id) {
		Pedido p =pedidoREPO.findById(id).orElse(null);
		
		if(p==null) {
			return -1;
		}
		else {
			Usuario user= usuarioService.getById(p.getUser().getId());
			user.getPedidos().remove(p);
			usuarioService.usuarioREPO.save(user);
			
			Iterator<LineaPedido> iterador = p.getLineas().iterator();
			while(iterador.hasNext()) {
				LineaPedido linea = iterador.next();
				lineaPedidoREPO.delete(linea);
			}
			pedidoREPO.delete(p);
			return 1;
		}
		
		
	}
	
	public LineaPedido existeProducto(Pedido p,LineaPedido lp) {
		List<LineaPedido> lineas = p.getLineas();
		LineaPedido respuesta =null;
		for (int i = 0; i < lineas.size(); i++) {
			if(lineas.get(i).getId_producto()==lp.getId_producto()) {
				respuesta=lineas.get(i);
			}
		}
		return respuesta;
	}
	

	
	
	
}
