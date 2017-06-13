package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import service.BookService;

@WebServlet(urlPatterns = {"/admin/bookInformation"})
public class BookInformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		List<Book> books = new BookService().selectAll();

		System.out.println(books.size());
		request.setAttribute("books", books);

		request.getRequestDispatcher("/admin/bookInformation.jsp").forward(request, response);

	}

}