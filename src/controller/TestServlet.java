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

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		List<Book> books = new BookService().selectAll();
<<<<<<< HEAD:src/admin/controller/TestServlet.java
		List<Circulation> circulations = new CirculationService().selectLimit(loginUser.getId());
		System.out.println(circulations.size());
=======
		List<Circulation> circulations = new CirculationService().selectMypage();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		System.out.println(loginUser.getId());
>>>>>>> 628f051bd5061e99e78f97d83daa2ddafa0a8f3b:src/controller/TestServlet.java

		request.setAttribute("circulations", circulations);
		request.setAttribute("books", books);
<<<<<<< HEAD:src/admin/controller/TestServlet.java
		request.setAttribute("circulations", circulations);
=======
		request.setAttribute("loginUser", loginUser);
>>>>>>> 628f051bd5061e99e78f97d83daa2ddafa0a8f3b:src/controller/TestServlet.java

		request.getRequestDispatcher("/test.jsp").forward(request, response);

	}

}