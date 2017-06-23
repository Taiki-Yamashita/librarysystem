package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Reservation;
import service.BookService;
import service.ReservationService;



@WebServlet(urlPatterns = { "/cancelingBook" })
public class CancelingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		List<Reservation> reservations = new ReservationService().selectAll();
//		request.setAttribute("reservations", reservations);
//		System.out.println(reservations);
//
//		request.getRequestDispatcher("user.jsp").forward(request, response);
//	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int num = Integer.parseInt(request.getParameter("num"));
		String time =request.getParameter("time");

		new BookService().cancelingBook(bookId, num, time);
		List<Reservation> reservingStatus= new ReservationService().selectReserving	(bookId);

		if(reservingStatus == null) new BookService().status(bookId);

		response.sendRedirect("./user");
	}
}
