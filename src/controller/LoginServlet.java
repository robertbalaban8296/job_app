package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DatabaseService;
import test.Test;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Cookie c;
		RequestDispatcher rd;
		
		if (DatabaseService.checkCredentials(username, password)) {
			c = new Cookie(username, password);
			response.addCookie(c);
			rd = request.getRequestDispatcher("main.jsp");
			Test.runExample();
			rd.forward(request, response);
		} else {
			response.sendRedirect("TryAgain.html");
		}
		
	}
}
