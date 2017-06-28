package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Circulation;
import beans.User;
import service.BookService;
import service.CirculationService;

@WebServlet(urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Book> books = new BookService().selectAll();
		List<Circulation> circulations = new CirculationService().selectMypage();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		System.out.println(loginUser.getId());

		request.setAttribute("circulations", circulations);
		request.setAttribute("books", books);
		request.setAttribute("loginUser", loginUser);

		request.getRequestDispatcher("/test.jsp").forward(request, response);

	}

}