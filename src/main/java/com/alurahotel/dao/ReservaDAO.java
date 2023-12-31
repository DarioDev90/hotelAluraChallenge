package com.alurahotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alurahotel.modelo.Reserva;

public class ReservaDAO {
	
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	// Method to create a new reservation

	public void crear(Reserva reserva) {
		try {
			con.setAutoCommit(false);
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago, tipo_habitacion) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				guardarReserva(reserva, statement);
				con.commit();
			} catch (SQLException e) {
				e.getMessage();
				con.rollback();
			}

		} catch (SQLException e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	public void guardarReserva(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setDouble(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		statement.setString(5, reserva.getTipoHabitacion());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println("Fue creada la siguiente reserva: " + reserva.toString());
			}
		}
	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago, tipo_habitacion FROM reservas");

			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fecha_entrada"),
							resultSet.getDate("fecha_salida"), resultSet.getDouble("valor"),
							resultSet.getString("forma_pago"), resultSet.getString("tipo_habitacion"));

					reservas.add(fila);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return reservas;
	}

	public int modificar(Integer id, Date fechaEntrada, Date fechaSalida, double valor, String formaPago, String tipoHabitacion) {

		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET " + "fecha_entrada = ?, "
					+ "fecha_salida = ?, " + "valor = ?, " + "forma_pago = ?, tipo_habitacion = ? WHERE id = ?");

			try (statement) {
				statement.setDate(1, fechaEntrada);
				statement.setDate(2, fechaSalida);
				statement.setDouble(3, valor);
				statement.setString(4, formaPago);
				statement.setString(5, tipoHabitacion);
				statement.setInt(6, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {

		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ? ;");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar(int id) {
		List<Reserva> reserva = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago, tipo_habitacion FROM reservas WHERE id = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fecha_entrada"),
							resultSet.getDate("fecha_salida"), resultSet.getDouble("valor"),
							resultSet.getString("forma_pago"), resultSet.getString("tipo_habitacion"));

					reserva.add(fila);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return reserva;
	}
}
