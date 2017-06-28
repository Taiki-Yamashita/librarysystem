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
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		List<Circulation> circulation = new CirculationService().selectLimit(loginUser.getId());
		if(circulation != null){
			int circulationSize=circulation.size();
			System.out.println(circulationSize);
			request.setAttribute("circulationSize", circulationSize);
		}
		List<Circulation> circulations = new CirculationService().selectMypage();

		request.setAttribute("books", books);
		request.setAttribute("circulation", circulation);
		request.setAttribute("circulations", circulations);


		request.getRequestDispatcher("/test.jsp").forward(request, response);

	}

}