package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import service.BookService;


@WebServlet(urlPatterns = {"/admin/addBook"})
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{

		Book book = new Book();
		book.setName(request.getParameter("name"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setCategory(request.getParameter("category"));
		book.setType(request.getParameter("type"));
		book.setPublishedDate(request.getParameter("publishedDate"));
		book.setLibraryId(request.getParameter("libraryId"));
		book.setShelfId(request.getParameter("shelfId"));
		book.setIsbnId(request.getParameter("isbnId"));

		new BookService().insert(book);



		response.sendRedirect("./admin/addBook");
	}
}
