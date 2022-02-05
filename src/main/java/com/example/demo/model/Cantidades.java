package com.example.demo.model;


public class Cantidades {

	private int cant1;
	private int cant2;
	private int cant3;
	private int cant4;
	private int cant5;
	private int cant6;

	
	public Cantidades() {
		super();
	}


	public Cantidades(int cant1, int cant2, int cant3, int cant4, int cant5, int cant6) {
		super();
		this.cant1 = cant1;
		this.cant2 = cant2;
		this.cant3 = cant3;
		this.cant4 = cant4;
		this.cant5 = cant5;
		this.cant6 = cant6;
	}


	public int getCant1() {
		return cant1;
	}


	public void setCant1(int cant1) {
		this.cant1 = cant1;
	}


	public int getCant2() {
		return cant2;
	}


	public void setCant2(int cant2) {
		this.cant2 = cant2;
	}


	public int getCant3() {
		return cant3;
	}


	public void setCant3(int cant3) {
		this.cant3 = cant3;
	}


	public int getCant4() {
		return cant4;
	}


	public void setCant4(int cant4) {
		this.cant4 = cant4;
	}


	public int getCant5() {
		return cant5;
	}


	public void setCant5(int cant5) {
		this.cant5 = cant5;
	}


	public int getCant6() {
		return cant6;
	}


	public void setCant6(int cant6) {
		this.cant6 = cant6;
	}


	@Override
	public String toString() {
		return "Cantidades [cant1=" + cant1 + ", cant2=" + cant2 + ", cant3=" + cant3 + ", cant4=" + cant4 + ", cant5="
				+ cant5 + ", cant6=" + cant6 + "]";
	}


	
	
	
}
