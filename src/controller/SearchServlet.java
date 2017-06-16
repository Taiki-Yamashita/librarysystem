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

@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Book> selectedBooks = (List<Book>)request.getSession().getAttribute("selectedBooks");
		List<Book> refinedBooks = (List<Book>)request.getSession().getAttribute("refinedBooks");

		if(selectedBooks == null && refinedBooks == null){
			request.getRequestDispatcher("/search.jsp").forward(request, response);
			return;
		}else{

			if(selectedBooks != null){
				if(isValid(selectedBooks, request)){
					request.setAttribute("booksCount", selectedBooks.size());
					request.setAttribute("books", selectedBooks);
				}
				else request.setAttribute("books", null);
				request.getRequestDispatcher("/search.jsp").forward(request, response);
			}
			if(refinedBooks != null){
				if(isValid(refinedBooks, request)){
					request.setAttribute("booksCount", refinedBooks.size());
					request.setAttribute("books", refinedBooks);
				}
				else request.setAttribute("books", null);
				request.getRequestDispatcher("/search.jsp").forward(request, response);
			}
		}
	}

	public boolean isValid(List<Book> books, HttpServletRequest request){

		List<String> errorMessages = new ArrayList<>();
		if(books.get(0).getId() == 0) errorMessages.add("検索結果が見つかりませんでした");

		if(errorMessages.isEmpty()) return true;
		else request.getSession().setAttribute("errorMessages", errorMessages);
		return false;
	}
}