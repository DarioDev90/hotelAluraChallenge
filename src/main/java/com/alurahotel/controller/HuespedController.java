package com.alurahotel.controller;

import java.sql.Date;
import java.util.List;

import com.alurahotel.dao.HuespedDAO;
import com.alurahotel.factory.ConnectionFactory;
import com.alurahotel.modelo.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().conectar());
	}

	public void crear(Huesped huesped, Integer reservaId) {
		huesped.setId(reservaId);
		huespedDAO.crear(huesped);

	}

	public Integer getReservaActual() {
		return huespedDAO.idReservaActual();
	}

	public List<Huesped> listar() {
		return huespedDAO.listar();
	}

	public List<Huesped> listar(Integer id) {
		return huespedDAO.listar(id);
	}
	
	public List<Huesped> listar(String apellido) {
		return huespedDAO.listar(apellido);
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, int reservaId) {
		return huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, reservaId);
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);

	}
}
