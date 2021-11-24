package com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_consolidado")
public class Consolidado {

	@Id
	private String id;
	private int id_ciudad;
	private String ciudad;
	private double total_ventas;
	
	public Consolidado() {
		// TODO Auto-generated constructor stub
	}

	public Consolidado(String id, int id_ciudad, String ciudad, double total_ventas) {
		super();
		this.id = id;
		this.id_ciudad = id_ciudad;
		this.ciudad = ciudad;
		this.total_ventas = total_ventas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getId_ciudad() {
		return id_ciudad;
	}

	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public double getTotal_ventas() {
		return total_ventas;
	}

	public void setTotal_ventas(double total_ventas) {
		this.total_ventas = total_ventas;
	}
	
}