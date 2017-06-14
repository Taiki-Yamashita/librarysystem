package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;


@WebServlet(urlPatterns = {"/admin/usersInformation"})
public class UsersInformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> users = new UserService().selectAll();

		request.setAttribute("users", users);

		request.getRequestDispatcher("/admin/usersInformation.jsp").forward(request, response);

	}

}
