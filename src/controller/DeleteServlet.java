package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Favorite;
import service.FavoriteService;



@WebServlet(urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException{
		Favorite favorite = new Favorite();
		new FavoriteService().delete(favorite, request.getParameter("userId"), request.getParameter("bookId"));
		request.getRequestDispatcher("/favorite.jsp").forward(request, response);
	}

}
