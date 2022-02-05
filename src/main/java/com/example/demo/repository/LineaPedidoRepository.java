package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Integer>{

	
	

		  List<LineaPedido> findByPedido(Pedido p);

		


}
