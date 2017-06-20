package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Reservation;
import beans.User;
import service.BookService;
import service.ReservationService;
import service.UserService;


@WebServlet(urlPatterns = { "/reservingBook" })
public class ReservingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.getRequestDispatcher("ranking.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int num = Integer.parseInt(request.getParameter("num"));
		new BookService().reservingBook(bookId, num);

		if(num ==1){

		Book reservingBook = new BookService().selectBook(bookId);
		String userId = (request.getParameter("userId"));
		User reservingUser = new UserService().selectUser(userId);

		Reservation addReservation = new Reservation();
		addReservation.setUserId(String.valueOf(reservingUser.getId()));
		addReservation.setBookId(String.valueOf(reservingBook.getId()));
		addReservation.setBookName(reservingBook.getName());
		addReservation.setLibraryId(request.getParameter("libraryId"));


		new ReservationService().insert(addReservation);
		}

		response.sendRedirect("./ranking");
	}
}
