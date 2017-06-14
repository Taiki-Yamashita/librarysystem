package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Information;
import service.InformationService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Information> informations = new InformationService().selectAll();
		request.setAttribute("informations", informations);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}