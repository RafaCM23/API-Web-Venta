package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="linea_pedido")
public class LineaPedido {

	private static int nserie=1;
	@Id
	public int lineaPedidoId;
	@ManyToOne(fetch= FetchType.EAGER)
	@JsonBackReference
	public Pedido pedido;
	@OneToOne
	@JoinColumn(name = "id_producto", nullable = false, foreignKey=@ForeignKey(name="Producto_ID"))
	public Producto producto;
	@Column(name = "cantidad", nullable = false)
	public int cantidad;
	
	
	
	public LineaPedido() {
		super();
		this.lineaPedidoId=nserie++;
	}

	public LineaPedido(Pedido pedido, Producto producto, int cantidad) {
		super();
		this.lineaPedidoId=nserie++;
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public LineaPedido(Producto producto, int cantidad) {
		super();
		this.lineaPedidoId=nserie++;
		this.producto = producto;
		this.cantidad = cantidad;
	}




	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	

	




	@Override
	public String toString() {
		return "LineaPedido [lineaPedidoId=" + lineaPedidoId + ", pedido=" + pedido + ", producto=" + producto
				+ ", cantidad=" + cantidad
				+ "]";
	}



	public static int getNserie() {
		return nserie;
	}


	public static void setNserie(int nserie) {
		LineaPedido.nserie = nserie;
	}


	public int getLineaPedidoId() {
		return lineaPedidoId;
	}


	public void setLineaPedidoId(int lineaPedidoId) {
		this.lineaPedidoId = lineaPedidoId;
	}



	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public int getId_pedido() {
		return this.pedido.getId();
	}
	public int getId_producto() {
		return this.producto.getId();
	}

		
	
}
