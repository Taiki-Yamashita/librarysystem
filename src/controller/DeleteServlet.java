package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FavoriteService;

@WebServlet(urlPatterns = {"/delete", "/admin/delete/*"})
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException{
		if(request.getParameter("favoriteBookId") != null) {
			new FavoriteService().delete(request.getParameter("favoriteUserId"), request.getParameter("favoriteBookId"));
			response.sendRedirect("./favorite");
			return;
		}
	}

}
