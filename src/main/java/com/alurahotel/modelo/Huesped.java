package com.alurahotel.modelo;

import java.sql.Date;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer reservaId;

	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			Integer reservaId) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			int reservaId) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public void setId(int id) {
		this.id = id;

	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public Integer getReservaId() {
		return reservaId;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "{Id: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido + ", Telefono: "
				+ this.telefono + ", Reserva: " + this.reservaId + "}";
	}

}
