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
import service.RequireService;

@WebServlet(urlPatterns = {"/request"})
public class RequireServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/require.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		if (isValid(request, messages)) {

			Require require = new Require();
			require.setUserName(request.getParameter("userName"));
			require.setBookName(request.getParameter("bookName"));
			require.setAuthor(request.getParameter("author"));
			require.setPublisher(request.getParameter("publisher"));
			new RequireService().insert(require);

			response.sendRedirect("./top");
		} else {
			session.setAttribute("errorMessages", messages);

			Require newRequire = getNewRequire(request);
			request.setAttribute("newRequire", newRequire);
			request.getRequestDispatcher("./require.jsp").forward(request, response);
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String userName = request.getParameter("userName");
		String bookName = request.getParameter("bookName");
		if (StringUtils.isEmpty(userName)) {
			messages.add("名前を入力してください");
			return false;
		}
		if (StringUtils.isEmpty(bookName)) {
			messages.add("名前を入力してください");
			return false;
		}
		return true;
	}

	private Require getNewRequire(HttpServletRequest request) {
		Require newRequire = new Require();

		newRequire.setUserName(request.getParameter("userName"));
		newRequire.setBookName(request.getParameter("bookName"));
		newRequire.setAuthor(request.getParameter("author"));
		newRequire.setPublisher(request.getParameter("publisher"));

		return newRequire;
	}
}
