package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;



@WebServlet("/SertvletTelefone")
public class SertvletTelefone extends ServletsGerenericUtil{
	private static final long serialVersionUID = 1L;
       
  
    public SertvletTelefone() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String iduser = request.getParameter("iduser");
		System.out.println(iduser);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
