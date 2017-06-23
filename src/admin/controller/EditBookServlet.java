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

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if(isValid(request, messages) == true) {
		Book editBook = getEditBook(request);
		request.setAttribute("editBook", editBook);

		new BookService().update(editBook);

		response.sendRedirect("./manageBook");
		}else {
			List<Library> libraries = new LibraryService().selectAll();
			request.setAttribute("libraries", libraries);

			Book editBook = getEditBook(request);
			request.setAttribute("editBook", editBook);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/admin/editBook.jsp").forward(request, response);
		}
	}

		private Book getEditBook(HttpServletRequest request) {

			String bookId = request.getParameter("book_id");
			int book_id = Integer.parseInt(bookId);
			Book editBook = new BookService().selectBook(book_id);
			String status =request.getParameter("status");


			editBook.setName(request.getParameter("name"));
			editBook.setAuthor(request.getParameter("author"));
			editBook.setPublisher(request.getParameter("publisher"));
			editBook.setCategory(getCategory(request));
			editBook.setType(getType(request));
			editBook.setLibraryId(request.getParameter("libraryId"));
			editBook.setShelfId(request.getParameter("shelfId"));
			editBook.setIsbnId(request.getParameter("isbnId"));
			editBook.setPublishedDate(request.getParameter("publishedDate"));
			if(status.equals("1")){
				editBook.setKeeping("1");
				editBook.setLending("0");
				editBook.setDisposing("0");
			}
			if(status.equals("2")){
				editBook.setKeeping("0");
				editBook.setLending("1");
				editBook.setDisposing("0");
			}
			if(status.equals("3")){
				editBook.setKeeping("0");
				editBook.setLending("0");
				editBook.setDisposing("1");
			}
			return editBook;
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
			String status = request.getParameter("status");

			if(StringUtils.isBlank(name) == true) {
					messages.add("書籍名を入力してください");
			}
			if(StringUtils.isBlank(author) == true) {
				messages.add("著者を入力してください");
			}
			if(StringUtils.isBlank(publisher) == true) {
				messages.add("出版社を入力してください");
			}
			if(StringUtils.isBlank(category) == true) {
				messages.add("カテゴリを選択してください");
			}
			if(StringUtils.isBlank(type) == true) {
				messages.add("種類を選択してください");
			}
			if(StringUtils.isBlank(publishedDate) == true) {
				messages.add("出版日を入力してください");
			}
			if(StringUtils.isBlank(libraryId) == true) {
				messages.add("図書館名を選択してください");
			}
			if(StringUtils.isBlank(shelfId) == true) {
				messages.add("棚番号を入力してください");
			}
			if(StringUtils.isBlank(isbnId) == true) {
				messages.add("ISBN番号を入力してください");
			}
			if(StringUtils.isBlank(isbnId) == true) {
				messages.add("ISBN番号を入力してください");
			}
			if(StringUtils.isBlank(status) == true) {
				messages.add("状態をを選択してください");
			}

			if(messages.size() ==0) {
				return true;
			}else {
				return false;
			}
		}



		public String getCategory(HttpServletRequest request){

			String category = request.getParameter("category");

			if(category.equals("1")) category = "文学";
			if(category.equals("2")) category = "経済";
			if(category.equals("3")) category = "芸能";
			if(category.equals("4")) category = "歴史";
			if(category.equals("5")) category = "学問";
			if(category.equals("6")) category = "政治";
			if(category.equals("7")) category = "暮らし";
			if(category.equals("8")) category = "教育";
			if(category.equals("9")) category = "SF";

			return category;
		}

		public String getType(HttpServletRequest request){

			String type = request.getParameter("type");

			if(type.equals("1")) type = "文庫";
			if(type.equals("2")) type = "新書";
			if(type.equals("3")) type = "雑誌";
			if(type.equals("4")) type = "コミックス";

			return type;
		}
	}