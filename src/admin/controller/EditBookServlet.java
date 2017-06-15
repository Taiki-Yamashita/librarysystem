package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import service.BookService;



@WebServlet(urlPatterns = { "/admin/editBook" })
public class EditBookServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookId = request.getParameter("id");

		Book editBook = new BookService().selectBook(Integer.parseInt(bookId));

		request.setAttribute("editBook", editBook);
		request.getRequestDispatcher("editBook.jsp").forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		Book editBook = getEditBook(request);
		request.setAttribute("editBook", editBook);

		new BookService().update(editBook);

		response.sendRedirect("./manageBook");

	}

		private Book getEditBook(HttpServletRequest request) {

			String bookId = request.getParameter("book_id");
			int book_id = Integer.parseInt(bookId);
			Book editBook = new BookService().selectBook(book_id);


			editBook.setName(request.getParameter("name"));
			editBook.setAuthor(request.getParameter("author"));
			editBook.setPublisher(request.getParameter("publisher"));
			editBook.setCategory(request.getParameter("category"));
			editBook.setType(request.getParameter("type"));
			editBook.setLibraryId(request.getParameter("libraryId"));
			editBook.setShelfId(request.getParameter("shelfId"));
			editBook.setIsbnId(request.getParameter("isbnId"));
			editBook.setPublishedDate(request.getParameter("publishedDate"));
			editBook.setKeeping(request.getParameter("keeping"));
			editBook.setLending(request.getParameter("lending"));
			editBook.setReserving(request.getParameter("reserving"));
			editBook.setDisposing(request.getParameter("disposing"));



			return editBook;
		}
	}