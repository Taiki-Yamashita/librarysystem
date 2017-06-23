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
//未読既読送信
		if(request.getParameter("flag") != null &&
				(request.getParameter("recieveId") != null)) {
			String[] idList = request.getParameterValues("recieveId");

			for(String id: idList) {
				System.out.println(7);
				new RecieveService().update(1, Integer.parseInt(id));
			}

		}
		if(request.getParameter("flag") != null &&
				(request.getParameter("recieveId2") != null)){
			String[] idList2 = request.getParameterValues("recieveId2");
			for(String id2: idList2) {
				System.out.println(9);
				new RecieveService().update(0, Integer.parseInt(id2));
			}
		}

//既読or未読表示
		if (request.getParameter("num") == null) {
			response.sendRedirect("./recieve");
			return;
		}
		else if(request.getParameter("num").matches("0") || request.getParameter("num").matches("1")) {
			List<Require> recieves = new RecieveService().select(Integer.parseInt(request.getParameter("num")));
			request.setAttribute("recieves", recieves);
			request.setAttribute("num", request.getParameter("num"));
			request.getRequestDispatcher("./recieve.jsp").forward(request, response);
		}
		else if(request.getParameter("num").matches("2")) {
			List<Require> recieves = new RecieveService().select();
			request.setAttribute("recieves", recieves);
			request.getRequestDispatcher("./recieve.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("./recieve");
			return;
		}
	}
}

