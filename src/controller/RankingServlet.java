package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Ranking;
import beans.User;
import service.RankingService;

@WebServlet(urlPatterns = { "/ranking" })
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		List<Ranking> circulations = new RankingService().circulationAll();
		List<Ranking> reservations = new RankingService().reservationAll();

		request.setAttribute("loginUser", loginUser);
		request.setAttribute("circulations", circulations);
		request.setAttribute("reservations", reservations);

		request.getRequestDispatcher("/ranking.jsp").forward(request, response);
	}
}
