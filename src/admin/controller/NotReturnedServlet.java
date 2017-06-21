package admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.beans.NotReturned;
import admin.service.NotReturnedService;
import beans.Circulation;
import service.BookService;
import service.CirculationService;

@WebServlet(urlPatterns = {"/admin/notReturned"})
public class NotReturnedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Date date = new Date();
		List<Circulation> circulations = null;
		try {
			circulations = new CirculationService().select(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		request.setAttribute("circulations", circulations);

		String bookId = request.getParameter("id");
		List<NotReturned> notReturnedlists = new NotReturnedService().select(Integer.parseInt(bookId));



		request.setAttribute("notReturnedlists", notReturnedlists);

		request.getRequestDispatcher("/admin/notReturned.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		String lending =request.getParameter("lending");
		String num = "0";

		new BookService().lendingBook(lending, num);
		response.sendRedirect("./notreturn");
	}
}