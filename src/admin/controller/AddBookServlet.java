package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Book;
import beans.Library;
import service.BookService;
import service.LibraryService;


@WebServlet(urlPatterns = {"/admin/addBook"})
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Library> libraries = new LibraryService().selectAll();
		String libraryId = request.getParameter("libraryId");

		request.setAttribute("libraries", libraries);
		request.setAttribute("libraryId", libraryId);


		request.getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{


		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		if(isValid(request, messages) == true) {

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


		response.sendRedirect("./addBook");
		}else{
			List<Library> libraries = new LibraryService().selectAll();
			request.setAttribute("libraries", libraries);

			Book newBook = getNewBook(request);
			request.setAttribute("newBook", newBook);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
		}
	}
	private Book getNewBook (HttpServletRequest request)
			throws IOException, ServletException {

		Book newBook = new Book();

		newBook.setName(request.getParameter("name"));
		newBook.setAuthor(request.getParameter("author"));
		newBook.setPublisher(request.getParameter("publisher"));
		newBook.setCategory(request.getParameter("category"));
		newBook.setType(request.getParameter("type"));
		newBook.setPublishedDate(request.getParameter("publishedDate"));
		newBook.setLibraryId(request.getParameter("libraryId"));
		newBook.setShelfId(request.getParameter("shelfId"));
		newBook.setIsbnId(request.getParameter("isbnId"));

		return newBook;

	}



	private boolean isValid(HttpServletRequest request, List<String> messages){

		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		String publishedDate = request.getParameter("publishedDate");
		String libraryId = request.getParameter("libraryId");
		String shelfId = request.getParameter("shelfId");
		String isbnId = request.getParameter("isbnId");

		if(StringUtils.isBlank(name) == true) {
				messages.add("名前を入力してください");
		}
		if(StringUtils.isBlank(author) == true) {
			messages.add("著者を入力してください");
		}
		if(StringUtils.isBlank(publisher) == true) {
			messages.add("出版社を入力してください");
		}
		if(StringUtils.isBlank(category) == true) {
			messages.add("カテゴリを入力してください");
		}
		if(StringUtils.isBlank(type) == true) {
			messages.add("種類を入力してください");
		}
		if(StringUtils.isBlank(publishedDate) == true) {
			messages.add("出版日を入力してください");
		}
		if(libraryId.matches("0") == true) {
			messages.add("図書館名を選択してください");
		}
		if(StringUtils.isBlank(shelfId) == true) {
			messages.add("棚番号を入力してください");
		}
		if(StringUtils.isBlank(isbnId) == true) {
			messages.add("ISBN番号を入力してください");
		}

		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}

}
