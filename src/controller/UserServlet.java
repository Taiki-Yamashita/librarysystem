package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Circulation;
import beans.Library;
import beans.Reservation;
import beans.User;
import service.BookService;
import service.CirculationService;
import service.LibraryService;
import service.ReservationService;
import service.UserService;

@WebServlet(urlPatterns = { "/user" })
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> users = new UserService().selectAll();
		List<Book> books = new BookService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();
		List<Circulation> circulations = new CirculationService().selectMypage();
		List<Reservation> reservations = new ReservationService().selectMypage();


		request.setAttribute("users", users);
		request.setAttribute("reservations", reservations);
		request.setAttribute("circulations", circulations);
		request.setAttribute("books", books);
		request.setAttribute("libraries", libraries);

		request.getRequestDispatcher("/user.jsp").forward(request, response);
	}
}