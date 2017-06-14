package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import service.BookService;

@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Book> books = new BookService().selectAll();
		List<Book> selectedBooks = (List<Book>)request.getSession().getAttribute("selectedBooks");

		System.out.println(selectedBooks);
		request.setAttribute("books", books);
		request.setAttribute("selectedBooks", selectedBooks);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		int selectBox = Integer.parseInt(request.getParameter("selectBox"));
		String freeWord = request.getParameter("freeWord");

		List<Book> selectedBooks = new BookService().getSelectedBooks(selectBox, freeWord);
		request.getSession().setAttribute("selectedBooks", selectedBooks);

		response.sendRedirect("./search");
	}
}