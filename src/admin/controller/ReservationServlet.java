package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.beans.Library;
import admin.beans.Reservation;
import admin.service.LibraryService;
import admin.service.ReservationService;
import admin.service.UserService;
import beans.User;

@WebServlet(urlPatterns = {"/admin/reservation"})
public class ReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Reservation> reservations = new ReservationService().selectAll();
		List<User> users = new UserService().getSelectAllUser();
		List<Library> libraries = new LibraryService().selectAll();

		System.out.println(reservations.get(0).getBookName());

		request.setAttribute("users", users);
		request.setAttribute("reservations", reservations);
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("/admin/reservation.jsp").forward(request, response);
	}
}
