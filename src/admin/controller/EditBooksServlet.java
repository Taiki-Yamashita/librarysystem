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



@WebServlet(urlPatterns = { "/editBooks" })
public class EditBooksServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	//	List<Book> books = new BookService().selectAll();
		String bookId = request.getParameter("id");

		int book_id = Integer.parseInt(bookId);
		Book editBook = new BookService().selectBook(book_id);

		request.setAttribute("editBook", editBook);
		request.getRequestDispatcher("/admin/bookInformation.jsp").forward(request, response);


	}


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		Book editBook = getEditBook(request);


		private Book getEditBook(HttpServletRequest request) {

			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String publisher = request.getParameter("publisher");
			String category = request.getParameter("category");


			Book editBook = new BookService().getUser(book_id);

			editBook.setName(request.getParameter("name"));
			editBook.setAuthor(request.getParameter("author"));
			editBook.setPublisher(request.getParameter("publisher"));
			editBook.setCategory(request.getParameter("category"));
		//	editBook.setDepartment_id(Integer.parseInt(request.getParameter("department")));

			return editBook;
		}
	}
}