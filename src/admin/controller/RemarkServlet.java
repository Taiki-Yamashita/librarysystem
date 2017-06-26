package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.RecieveService;
import beans.Require;

@WebServlet(urlPatterns = {"/admin/remark"})

public class RemarkServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Require receive = new RecieveService().selectUser(Integer.parseInt(request.getParameter("id")));

		request.setAttribute("receive", receive);
		request.getRequestDispatcher("/admin/remark.jsp").forward(request, response);
	}
}
