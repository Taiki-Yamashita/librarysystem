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
		//List<Require> recieves = new RecieveService().select(Integer.parseInt(request.getParameter("num")));

		request.setAttribute("recieves", recieves);

		request.getRequestDispatcher("./recieve.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		if(request.getParameter("num") != null) {
			List<Require> recieves = new RecieveService().select(Integer.parseInt(request.getParameter("num")));
			request.setAttribute("recieves", recieves);
			request.getRequestDispatcher("./recieve.jsp").forward(request, response);

		} else if(request.getParameter("num") != null &&
				(request.getParameter("num") != null || request.getParameter("id") != null)) {
			int flag = Integer.parseInt(request.getParameter("flag"));
			int id = Integer.parseInt(request.getParameter("id"));
			new RecieveService().update(flag, id);
			request.getRequestDispatcher("./recieve.jsp").forward(request, response);

		} else {
			response.sendRedirect("./recieve");
		}



	}
}

