package com.alurahotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource datasourse;

	public ConnectionFactory() {

		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_hotel?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("1234");
		pooledDataSource.setMaxPoolSize(10);

		this.datasourse = pooledDataSource;
	}

	public Connection conectar() {
		try {
			return this.datasourse.getConnection();
		} catch (SQLException e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

}
