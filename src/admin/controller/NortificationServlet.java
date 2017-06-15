package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Notification;
import service.NotificationService;

@WebServlet(urlPatterns = { "/admin/notification" })
public class NortificationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/admin/notification.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{

		Notification nortification = new Notification();
		nortification.setMessage(request.getParameter("message"));
		nortification.setLibraryId(request.getParameter("libraryId"));
		nortification.setRegisteredDate(request.getParameter("RegisteredDate"));

		new NotificationService().insert(nortification);

		response.sendRedirect("./manage");
	}
}
