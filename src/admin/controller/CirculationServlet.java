package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Circulation;
import service.BookService;
import service.CirculationService;

@WebServlet(urlPatterns = {"/admin/circulation"})
public class CirculationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Circulation> circulations = new CirculationService().selectAll();
		request.setAttribute("circulations", circulations);

		request.getRequestDispatcher("/admin/circulation.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		String lending =request.getParameter("lending");
		String num = "0";

		new BookService().lendingBook(lending, num);
		response.sendRedirect("./circulation");
	}

}
