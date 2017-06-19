package admin.controller;

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

import beans.Circulation;
import beans.User;
import service.BookService;
import service.CirculationService;
import service.UserService;



@WebServlet(urlPatterns = { "/admin/lendingBook" })
public class LendingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("manageBook.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {
		HttpSession session = request.getSession();

		String lending = request.getParameter("bookId");
		String num = request.getParameter("num");
		List<String> messages = new ArrayList<String>();
		Circulation circulation = new Circulation();

		User users =new UserService().selectUser((request.getParameter("userId")));


		if(num.matches("1") && isValid(request, messages, users)) {
			circulation.setUserId(request.getParameter("userId"));
			circulation.setBookId(request.getParameter("bookId"));
			circulation.setLibraryId(request.getParameter("libraryId"));
			new CirculationService().insert(circulation);
			new BookService().lendingBook(lending, num);
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("userId", request.getParameter("userId"));
		}

		if(num.matches("0")) {
			circulation.setBookId(request.getParameter("bookId"));
			circulation.setLibraryId(request.getParameter("libraryId"));
			new CirculationService().update(circulation);
			new BookService().lendingBook(lending, num);
		}
		response.sendRedirect("./manageBook");
	}

	private boolean isValid(HttpServletRequest request, List<String> messages, User users) {
		if(StringUtils.isBlank(request.getParameter("userId"))) {
			messages.add("ユーザーIDを入力してください");
		} else if(users == null) {
			messages.add("存在しないユーザーです");
		}
		else if(!request.getParameter("userId").matches("\\d{1,}$")) {
			messages.add("ユーザーIDは半角数字です");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}
}
