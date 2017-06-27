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

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		HttpSession session = request.getSession();
		if(loginUser == null) {
			List<String> messages = new ArrayList<String>();
			messages.add("ログインしてください");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}
		//String loginId = loginUser.getLoginId();
		User editUser = new UserService().selectUser(String.valueOf(loginUser.getId()));

		request.setAttribute("editUser", editUser);
		request.setAttribute("loginUser", loginUser);

		request.getRequestDispatcher("renewPassword.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User editUser = getEditUser(request);
		if (isValid(request, messages)) {
			new UserService().update(editUser);
			response.sendRedirect("./");
			return;
		}
		session.setAttribute("errorMessages", messages);

		response.sendRedirect("./renewPassword");
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {
		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		if(!StringUtils.isBlank(request.getParameter("password"))) {
			String password = request.getParameter("password");
			String confirmedpassword = request.getParameter("confirmedPassword");
			if (StringUtils.isBlank(password) == true) {
				messages.add("パスワードを入力してください");
//			} else if ( !password.matches("\\w{6,255}$") ) {
//				messages.add("パスワードは半角数字6文字以上255文字以下で入力してください");
			}
			if (!password.matches(confirmedpassword)) {
				messages.add("パスワードとパスワード確認が不一致です");
			}
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
