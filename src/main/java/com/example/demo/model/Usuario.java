package com.example.demo.model;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nickname", nullable = false)
	private String nickname;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name="direccion")
	private String direccion;
	@Column(name="telefono")
	private int tlfn;
	@Column(name="correo")
	private String correo;
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER)
	private List<Pedido> pedidos;
	
	
	public Usuario() {
		this.pedidos= new ArrayList<Pedido>();
	}
	
	public Usuario(String nick) {
		this.nickname=nick;
		this.pedidos= new ArrayList<Pedido>();
	}
	
	
	

	
	
	public Usuario(int id,String nickname, String password, String direccion, int tlfn, String correo) {
		super();
		this.id=id;
		this.nickname = nickname;
		this.password = password;
		this.direccion = direccion;
		this.tlfn = tlfn;
		this.correo = correo;
	}

	public Usuario(int id,String nickname, String password) {
		super();
		this.id=id;
		this.nickname = nickname;
		this.password = password;
		this.pedidos= new ArrayList<Pedido>();
	}


	



	public Usuario(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void addPedido(Pedido p) {
		pedidos.add(p);
	}
	
	
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTlfn() {
		return tlfn;
	}

	public void setTlfn(int tlfn) {
		this.tlfn = tlfn;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nickname, other.nickname);
	}
	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void borrarPedido(Pedido p) {
		if (this.pedidos.contains(p)) {
			this.pedidos.remove(this.pedidos.indexOf(p));
		}
	}

	@Override
	public String toString() {
		StringBuilder respuesta = new StringBuilder();
		respuesta.append("Nick:"+this.nickname+"\n");
		if (!pedidos.isEmpty()) {
			for (Pedido p: pedidos) {
				respuesta.append(p.toString());
			}
		}
		
		return respuesta.toString();
	}
	
	public Pedido getPedido(int id) {
		Pedido buscado = new Pedido();
		buscado.setId(id);
		Pedido.setNserie(Pedido.getNserie()-1);
		
		int encontrado = this.pedidos.indexOf(buscado);
		if (encontrado!=-1) {
			return this.pedidos.get(encontrado);
		}
		else {
			return null;
		}
	}
}
