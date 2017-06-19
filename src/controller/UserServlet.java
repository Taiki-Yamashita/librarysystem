package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Reservation;
import beans.User;
import service.ReservationService;

@WebServlet(urlPatterns = { "/user" })
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<Reservation> reservations = new ReservationService().selectAllView();
		request.setAttribute("reservations", reservations);

		User loginUser = (User) session.getAttribute("loginUser");

		request.setAttribute("loginUser", loginUser);


		request.getRequestDispatcher("/user.jsp").forward(request, response);
	}
}