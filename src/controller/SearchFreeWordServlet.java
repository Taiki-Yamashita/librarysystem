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

@WebServlet(urlPatterns = { "/searchFreeWord" })
public class SearchFreeWordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		String selectBox = request.getParameter("selectBox");
		String freeWord = request.getParameter("freeWord");
		String condition = request.getParameter("condition");

		List<Book> selectedBooks = new BookService().getSelectedBooks(selectBox, freeWord, condition);
		request.getSession().setAttribute("selectBox", new BookService().getMapCategory().get(selectBox));
		request.getSession().setAttribute("selectBoxId", selectBox);
		request.getSession().setAttribute("freeWord", freeWord);
		request.getSession().setAttribute("selectedBooks", selectedBooks);
		request.getSession().setAttribute("condition", condition);

		response.sendRedirect("./search");
	}
}