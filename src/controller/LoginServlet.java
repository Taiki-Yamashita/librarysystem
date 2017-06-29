package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		if(isValid(request)){
			response.sendRedirect("./");
			return;
		}
		response.sendRedirect("./login");
	}

	public boolean isValid(HttpServletRequest request){

		List<String> messages = new ArrayList<>();

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		User user = new UserService().getLoginUser(loginId, password);

		if(user == null || user.getStopping().matches("1")){
			messages.add("ログインに失敗しました");
			request.getSession().setAttribute("errorMessages", messages);
			return false;
		}

		request.getSession().setAttribute("loginUser", user);
		return true;
	}
}