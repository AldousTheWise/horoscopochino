package com.elsantisimo.dbconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
	private static final String URL = "jdbc:postgresql://localhost:5432/db_horoscopo";
	private static final String USER = "postgres";
	private static final String PASSWORD = "aldo1984";
	private static Connection conn;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar el driver de PostgreSQL: " + e.getMessage());
		}
	}
	
	public static Connection getConexion() throws SQLException {
		
		
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		} catch (SQLException e) {
			System.err.println("Error en la conexión " + e.getMessage());
			throw e;
		}
		
		return conn;
	}
}
