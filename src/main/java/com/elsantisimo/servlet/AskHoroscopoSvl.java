package com.elsantisimo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.elsantisimo.DAO.HoroscopoDAO;
import com.elsantisimo.DAO.UsuarioDAO;
import com.elsantisimo.model.Usuario;

/**
 * Servlet implementation class AskHoroscopoSvl
 */
@WebServlet("/askHoroscopo")
public class AskHoroscopoSvl extends HttpServlet {
	private HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AskHoroscopoSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");

	    if (usuario != null) {
	        LocalDate fechaNacimiento = usuario.getFechaNacimiento();
	        String animal = horoscopoDAO.getAnimal(fechaNacimiento);

	        if (animal != null) {
	            usuarioDAO.actualizarAnimal(usuario.getId(), animal);
	            usuario.setAnimal(animal);
	            sesion.setAttribute("usuario", usuario);
	            request.setAttribute("mensaje", "Registro correcto");
	        } else {
	        	request.setAttribute("mensaje", "No se encontró un animal para ti. Acaso estás vivo?");
	        }

	        request.setAttribute("animal", animal);
	        request.getRequestDispatcher("index.jsp?view=askHoroscopo").forward(request, response);
	    } else {
	        response.sendRedirect("index.jsp?view=adminIF");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		
		if(usuario != null) {
			LocalDate fechaNacimiento = usuario.getFechaNacimiento();
			String animal = horoscopoDAO.getAnimal(fechaNacimiento);
			
			if(animal != null) {
				usuarioDAO.actualizarAnimal(usuario.getId(), animal);
				usuario.setAnimal(animal);
				sesion.setAttribute("usuario", usuario);
			}
				request.setAttribute("animal", animal);
				request.getRequestDispatcher("index.jsp?view=askHoroscopo").forward(request, response);
				
		    } else {
				response.sendRedirect("index.jsp");
			}		
	}
}
