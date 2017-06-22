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
import service.LibraryService;
import service.NotificationService;

@WebServlet(urlPatterns = { "/information" })
public class InformationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Notification information =
				new NotificationService().select(Integer.parseInt(request.getParameter("id")));

		List<Library> libraries = new LibraryService().selectAll();

		request.setAttribute("libraries", libraries);

		request.setAttribute("information", information);

		request.getRequestDispatcher("information.jsp").forward(request, response);

	}
}
