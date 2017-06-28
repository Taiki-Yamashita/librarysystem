package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;
@WebServlet(urlPatterns = { "/renewPassword"})

public class RenewPasswordServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("renewPassword.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User editUser = getEditUser(request);
		if (isValid(request)) {
			new UserService().update(editUser);
			session.setAttribute("doneMessages", "ユーザー情報を更新しました");
			response.sendRedirect("./");
			return;
		}

		response.sendRedirect("./renewPassword");
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {
		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("newPassword"));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		String loginId = request.getParameter("loginId");
		String newPassword = request.getParameter("newPassword");
		String confirmedPassword = request.getParameter("confirmedPassword");

		if(StringUtils.isBlank(loginId)) messages.add("ログインIDを入力してください");
		else if(loginId.length() < 6) messages.add("ログインIDは6文字以上で入力してください");
		else if(!loginId.matches("\\w{6,20}")) messages.add("ログインIDは半角英数字で入力してください");

		if(StringUtils.isBlank(newPassword) && StringUtils.isBlank(confirmedPassword));
		else if(StringUtils.isBlank(newPassword) || StringUtils.isBlank(confirmedPassword)){
			messages.add("新しいパスワードと確認用パスワードは一致させてください");
		}
		else{
			if(!newPassword.equals(confirmedPassword)) messages.add("新しいパスワードと確認用パスワードは一致させてください");
			if(newPassword.length() < 6 || confirmedPassword.length() < 6) messages.add("パスワードは6文字以上で登録してください");
		}

		if (messages.size() == 0) return true;

		request.getSession().setAttribute("errorMessages", messages);
		return false;
	}
}
