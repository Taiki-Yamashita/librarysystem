package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Information;
import service.InformationService;

@WebServlet(urlPatterns = { "/admin/information" })
public class InformationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/admin/information.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{

		Information information = new Information();
		information.setMessage(request.getParameter("message"));
		information.setLibraryId(request.getParameter("libraryId"));
		information.setRegisteredDate(request.getParameter("RegisteredDate"));

		new InformationService().insert(information);


		response.sendRedirect("./manage");
	}
}
