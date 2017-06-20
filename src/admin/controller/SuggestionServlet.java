package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Introduction;
import service.IntroductionService;

@WebServlet(urlPatterns = { "/admin/suggestion" })
public class SuggestionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/admin/suggestion.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		Introduction introduction = new Introduction();

		introduction.setUserId(request.getParameter("userId"));
		introduction.setBookId(request.getParameter("bookId"));

		new IntroductionService().insert(introduction);

		response.sendRedirect("./manage");
	}
}
