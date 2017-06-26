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

import beans.Require;
import beans.User;
import service.RequireService;

@WebServlet(urlPatterns = {"/require"})
public class RequireServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		HttpSession session = request.getSession();
		if(loginUser == null) {
			List<String> messages = new ArrayList<String>();
			messages.add("ログインしてください");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}
		request.getRequestDispatcher("/require.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Require require = getRequire(request);
		if (isValid(require, request)) {
			new RequireService().insert(require);
			response.sendRedirect("./");
		} else {
			request.setAttribute("newRequire", require);
			request.getRequestDispatcher("./require.jsp").forward(request, response);
		}

	}

	private boolean isValid(Require require, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		String bookName = request.getParameter("bookName");
		String comment = request.getParameter("comment");

		if (StringUtils.isEmpty(bookName)) {
			messages.add("書籍名を入力してください");
		}
		if (comment.length() > 500) {
			messages.add("備考は500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			request.getSession().setAttribute("errorMessages", messages);
			return false;
		}
	}

	private Require getRequire(HttpServletRequest request) {

		Require require = new Require();

		require.setUserName(request.getParameter("userName"));
		require.setBookName(request.getParameter("bookName"));
		require.setAuthor(request.getParameter("author"));
		require.setPublisher(request.getParameter("publisher"));
		require.setComment(request.getParameter("comment"));

		return require;
	}
}
