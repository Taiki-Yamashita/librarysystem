package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.UserService;

@WebServlet("/admin/renewUser")
public class RenewUserServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {
		int renewUserId = Integer.parseInt(request.getParameter("renewUserId"));
		String renewUserLoginId = request.getParameter("renewUserLoginId");
		new UserService().update(renewUserId, renewUserLoginId);

		response.sendRedirect("./manageUser");
	}
}
