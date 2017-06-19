package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Ranking;
import service.RankingService;

@WebServlet(urlPatterns = { "/ranking" })
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Ranking> countLists = new RankingService().countAll();

		request.setAttribute("countLists", countLists);

		request.getRequestDispatcher("/ranking.jsp").forward(request, response);
	}
}
