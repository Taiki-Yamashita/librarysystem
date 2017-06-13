package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		User user = new UserService().getLoginUser(loginId, password);

		if(user != null){
			request.getSession().setAttribute("loginUser", user);
			response.sendRedirect("./");
		} else response.sendRedirect("./login");

	}
}