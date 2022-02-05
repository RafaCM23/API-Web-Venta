package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("rawtypes")
@Entity
@Table(name="pedidos")
public class Pedido implements Comparable{
	
	private static int nserie=1;
	@Id
	private int id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<LineaPedido> lineas;
	@ManyToOne
	@JsonIgnore
	private Usuario user;
	@Column(name="Fecha")
	private Date fecha;
	@Column(name="direccion")
	private String direccion;
	@Column(name="telefono")
	private int tlfn;
	@Column(name="correo")
	private String correo;
	


	public Pedido() {
		this.lineas=new ArrayList<LineaPedido>();
		this.id = nserie++;
		this.fecha=new Date(System.currentTimeMillis());
	}
	
	
	


	public Pedido(Usuario user, String direccion, int tlfn, String correo) {
		super();
		this.user = user;
		this.lineas=new ArrayList<LineaPedido>();
		this.id = nserie++;
		this.fecha=new Date(System.currentTimeMillis());
		this.direccion = direccion;
		this.tlfn = tlfn;
		this.correo = correo;
	}





	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return id == other.id;
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
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public String imprimeLinea(int posicion) {
		return lineas.get(posicion).toString();
	}


	public List<LineaPedido> getLineas() {
		return lineas;
	}
	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}


	public void addLinea(LineaPedido p) {
		this.lineas.add(p);
	}

	public void borrarLinea(int pos) {
		this.lineas.remove(pos);
	}

	@Override
	public String toString() {
		StringBuilder respuesta = new StringBuilder();
		respuesta.append("Pedido= ID:"+this.id+" \n");
		for (int i = 0; i < lineas.size(); i++) {
			respuesta.append(lineas.get(i).toString()+ "\n");
			
		}
		return respuesta.toString();
	}


	public static int getNserie() {
		return nserie;
	}


	public static void setNserie(int nserie) {
		Pedido.nserie = nserie;
	}
	
	
	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	


	@Override
	public int compareTo(Object o) {
		int resultado=0;
		
		if(this.fecha.after(((Pedido)o).getFecha())){
			resultado=-1;
		}
		if(this.fecha.before(((Pedido)o).getFecha())){
			resultado=1;
		}
		return resultado;
	}
	
	
	
}
