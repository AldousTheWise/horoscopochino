package com.elsantisimo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.elsantisimo.dbconexion.DBConexion;

/**
 * Servlet implementation class DBConexionSvl
 */
@WebServlet("/conexion")
public class DBConexionSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBConexionSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	
    	try {
    		Connection conn = DBConexion.getConexion();
    		if(conn != null) {
    			System.out.println("Conexión a la BDD exitosa");
    		} else {
    			System.out.println("Error al conectar a la BDD");
    		}
			
		} catch (SQLException e) {
			System.err.println("Error al establecer conexión a la base de datos: " + e.getMessage());
			throw new ServletException("No se pudo establecer conexión con la base de datos", e); 
		}
    }
      

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Conexion a la bdd verificada.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
