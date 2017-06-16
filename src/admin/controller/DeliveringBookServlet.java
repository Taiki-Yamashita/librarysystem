package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;



@WebServlet(urlPatterns = { "/admin/deliveringBook" })
public class DeliveringBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("reservation.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		int delivering = Integer.parseInt(request.getParameter("bookId"));

		int num = Integer.parseInt(request.getParameter("num"));
		new BookService().deliveringBook(delivering, num);



		response.sendRedirect("./reservation");
	}
}
