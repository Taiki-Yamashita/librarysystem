package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.RecieveService;
import beans.Require;

@WebServlet(urlPatterns = {"/admin/recieve"})
public class RecieveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Require> recieves = new RecieveService().select();

		request.setAttribute("recieves", recieves);

		request.getRequestDispatcher("/admin/recieve.jsp").forward(request, response);
	}
}

