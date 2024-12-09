package com.elsantisimo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.elsantisimo.dbconexion.DBConexion;

public class HoroscopoDAO {
	
	
	public String getAnimal(LocalDate fechaNacimiento) {
		String sql = "SELECT animal FROM horoscopo WHERE ? BETWEEN fecha_inicio AND fecha_fin";
		
		try(Connection conn = DBConexion.getConexion();
			PreparedStatement pstm = conn.prepareStatement(sql)){
			
			pstm.setDate(1, java.sql.Date.valueOf(fechaNacimiento));
			
			try(ResultSet rs = pstm.executeQuery()) {
				if(rs.next()) {
					return rs.getString("animal");
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Desconocido";
			
	}
     
}

