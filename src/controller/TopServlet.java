package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Library;
import beans.Notification;
import beans.User;
import service.LibraryService;
import service.NotificationService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Notification> informations = new NotificationService().selectAll();

		List<Library> libraries = new LibraryService().selectAll();

		User loginUser = (User) request.getSession().getAttribute("loginUser");

		request.setAttribute("informations", informations);
		request.setAttribute("libraries", libraries);
		request.setAttribute("loginUser", loginUser);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}