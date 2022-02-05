package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", nullable = false)
	private String nombre;

	
	
	public Producto() {
		super();
	}

	public Producto(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	
	}
	public Producto(int id, String nombre, String img) {
		super();
		this.id = id;
		this.nombre = nombre;

	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Producto= ID:" + id + ", nombre=" + nombre;
	}

	
	
}
