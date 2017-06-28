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

			Book book = getNewBook(request);
			new BookService().insert(book);

			response.sendRedirect("./manageBook");
		}else{
			List<Library> libraries = new LibraryService().selectAll();
			request.setAttribute("libraries", libraries);

			Book newBook = getNewBook(request);
			request.setAttribute("newBook", newBook);
			request.setAttribute("publishedDate", request.getParameter("publishedDate"));
			request.setAttribute("publishedDate2", request.getParameter("publishedDate2"));
			request.setAttribute("publishedDate3", request.getParameter("publishedDate3"));

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
		}
	}
	private Book getNewBook (HttpServletRequest request)
			throws IOException, ServletException {

		Book newBook = new Book();
		String year = request.getParameter("publishedDate");
		String month = request.getParameter("publishedDate2");
		String day = request.getParameter("publishedDate3");
		String publishedDate = new String(year + "-" + month + "-"+ day + " 00:00:00");

		newBook.setName(request.getParameter("name"));
		newBook.setAuthor(request.getParameter("author"));
		newBook.setPublisher(request.getParameter("publisher"));
		newBook.setCategory(getCategory(request));
		newBook.setType(getType(request));
		newBook.setPublishedDate(publishedDate);
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
		String libraryId = request.getParameter("libraryId");
		String shelfId = request.getParameter("shelfId");
		String isbnId = request.getParameter("isbnId");
		String year = request.getParameter("publishedDate");
		String month = request.getParameter("publishedDate2");
		String day = request.getParameter("publishedDate3");
		String publishedDate = new String(year + "-" + month + "-"+ day + " 00:00:00");


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
		if(StringUtils.isBlank(libraryId) == true) {
			messages.add("図書館名を選択してください");
		}
		if(StringUtils.isBlank(shelfId) == true) {
			messages.add("棚番号を入力してください");
		}
		if(StringUtils.isBlank(isbnId) == true) {
			messages.add("ISBN番号を入力してください");
		}
		if(StringUtils.isEmpty(publishedDate) == true ) {
			messages.add("日付のフォーマットが不正です");
		}

		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}

	public String getCategory(HttpServletRequest request){

		String category = request.getParameter("category");
		if(category ==null)	{
			request.getRequestDispatcher("/admin/addBook.jsp");
			return null;
		}
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
		if(type ==null)	{
			request.getRequestDispatcher("/admin/addBook.jsp");
			return null;
		}
		if(type.equals("1")) type = "文庫";
		if(type.equals("2")) type = "新書";
		if(type.equals("3")) type = "雑誌";
		if(type.equals("4")) type = "コミックス";

		return type;
	}
}
