package controller;

import java.io.IOException;
import java.util.ArrayList;
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

		request.setAttribute("books", books);
		if(selectedBooks == null){
			request.getRequestDispatcher("/search.jsp").forward(request, response);
			return;
		}

		if(isValid(selectedBooks, request)) request.getSession().setAttribute("booksCount", selectedBooks.size());
		else request.getSession().setAttribute("selectedBooks", null);

		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	public boolean isValid(List<Book> selectedBooks, HttpServletRequest request){

		List<String> errorMessages = new ArrayList<>();

		if(selectedBooks.get(0).getId() == 0) errorMessages.add("検索結果が見つかりませんでした");

		if(errorMessages.isEmpty()) return true;
		else request.getSession().setAttribute("errorMessages", errorMessages);
		return false;
	}
}