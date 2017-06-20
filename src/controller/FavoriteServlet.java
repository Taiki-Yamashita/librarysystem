package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Favorite;
import beans.User;
import service.FavoriteService;

@WebServlet(urlPatterns = { "/favorite" })
public class FavoriteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("loginUser");
//		if(String.valueOf(user.getId()) != null){
//			user.getId();
//		}
//
		request.setAttribute("user", user);

		List<Favorite> favorites = new FavoriteService().selectAll();

		request.setAttribute("favorites", favorites);

		request.getRequestDispatcher("/favorite.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Favorite favorite = new Favorite();

		favorite.setUserId(request.getParameter("userId"));
		favorite.setBookId(request.getParameter("bookId"));
		new FavoriteService().insert(favorite);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}
}