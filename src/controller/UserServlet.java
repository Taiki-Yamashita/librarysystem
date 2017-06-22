package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Library;
import beans.Reservation;
import service.BookService;
import service.LibraryService;
import service.ReservationService;

@WebServlet(urlPatterns = { "/user" })
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Reservation> reservations = new ReservationService().selectAll();
		List<Book> books = new BookService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();

		System.out.println(reservations);

		request.setAttribute("reservations", reservations);
		request.setAttribute("books", books);
		request.setAttribute("libraries", libraries);

		request.getRequestDispatcher("/user.jsp").forward(request, response);
	}
}