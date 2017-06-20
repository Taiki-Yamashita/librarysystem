package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Favorite;
import beans.User;
import service.BookService;
import service.FavoriteService;

@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Favorite> favorites = new FavoriteService().selectAll();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		List<Book> selectedBooks = (List<Book>)request.getSession().getAttribute("selectedBooks");

		if(selectedBooks == null){
			request.getRequestDispatcher("/search.jsp").forward(request, response);
			return;
		}
		else{
			if(isValid(selectedBooks, request)){
				request.setAttribute("booksCount", selectedBooks.size());
				request.setAttribute("pageCountList", getPageCount(selectedBooks.size()));
				request.setAttribute("pageNumber", request.getParameter("pageNumber"));
				request.setAttribute("books", selectedBooks);
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("favorites", favorites);
			}
			else request.setAttribute("books", null);
			request.getRequestDispatcher("/search.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sort = "";
		if(request.getParameter("sort") != null) sort = request.getParameter("sort");
		String bookStatus = request.getParameter("bookStatus");

		/*search*/
		if(request.getParameter("isSearching") != null){

			/*freeWord*/
			String selectBox = request.getParameter("selectBox");
			String freeWord = request.getParameter("freeWord");
			String condition = request.getParameter("condition");

			/*refine*/
			List<String> newBooks = getNewBooks(request);
			List<String> libraries = getLibraries(request);
			List<String> categories = getCategories(request);
			List<String> types = getTypes(request);

			/*freeWord*/
			if(request.getParameter("throughSearching") != null){
				selectBox = request.getParameter("selectBoxForSort");
				freeWord = request.getParameter("freeWordForSort");
				condition = request.getParameter("conditionForSort");
				bookStatus = request.getParameter("bookStatus");
			}

			List<Book> selectedBooks = new BookService().getSelectedBooks(selectBox, freeWord, condition, sort, bookStatus,
					newBooks, libraries, categories, types);

			/*freeWord*/
			request.getSession().setAttribute("selectBox", new BookService().getMapCategory().get(selectBox));
			request.getSession().setAttribute("selectBoxId", selectBox);
			request.getSession().setAttribute("freeWord", freeWord);
			request.getSession().setAttribute("condition", condition);

			/*refine*/
			request.getSession().setAttribute("newBooks", newBooks);
			request.getSession().setAttribute("libraries", libraries);
			request.getSession().setAttribute("categories", categories);
			request.getSession().setAttribute("types", types);

			request.getSession().setAttribute("selectedBooks", selectedBooks);
			request.getSession().setAttribute("sort", sort);
			request.getSession().setAttribute("bookStatus", bookStatus);
			request.getSession().setAttribute("throughSearching", "1");

			if(request.getParameter("pageNumber") == null) response.sendRedirect("./search?pageNumber=1");
			else response.sendRedirect("./search?pageNumber=" + request.getParameter("pageNumber"));
		}
	}

	public boolean isValid(List<Book> books, HttpServletRequest request){

		List<String> errorMessages = new ArrayList<>();
		if(books.get(0).getId() == 0) errorMessages.add("検索結果が見つかりませんでした");

		if(errorMessages.isEmpty()) return true;
		else request.getSession().setAttribute("errorMessages", errorMessages);
		return false;
	}

	public List<String> getNewBooks(HttpServletRequest request){

		List<String> newBooks = new ArrayList<>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -31);
		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());

		if(request.getParameter("newBooks") != null){
			newBooks.add(currentDate);
			request.getSession().setAttribute("checkNewBooks", "1");
		}
		else{
			newBooks.add("1500-01-01 00:00:00");
			request.getSession().setAttribute("checkNewBooks", "0");
		}

		return newBooks;
	}

	public List<String> getLibraries(HttpServletRequest request){

		List<String> libraries = new ArrayList<>();
		if(request.getParameter("library1") != null) libraries.add(request.getParameter("library1"));
		if(request.getParameter("library2") != null) libraries.add(request.getParameter("library2"));
		if(request.getParameter("library3") != null) libraries.add(request.getParameter("library3"));
		if(request.getParameter("library4") != null) libraries.add(request.getParameter("library4"));
		if(request.getParameter("library5") != null) libraries.add(request.getParameter("library5"));

		return libraries;
	}

	public List<String> getCategories(HttpServletRequest request){

		List<String> categories = new ArrayList<>();

		if(request.getParameter("category1") != null) categories.add("文学");
		if(request.getParameter("category2") != null) categories.add("経済");
		if(request.getParameter("category3") != null) categories.add("芸能");
		if(request.getParameter("category4") != null) categories.add("歴史");
		if(request.getParameter("category5") != null) categories.add("学問");
		if(request.getParameter("category6") != null) categories.add("政治");
		if(request.getParameter("category7") != null) categories.add("暮らし");
		if(request.getParameter("category8") != null) categories.add("教育");
		if(request.getParameter("category9") != null) categories.add("SF");

		return categories;
	}

	public List<String> getTypes(HttpServletRequest request){

		List<String> types = new ArrayList<>();

		if(request.getParameter("type1") != null) types.add("文庫");
		if(request.getParameter("type2") != null) types.add("新書");
		if(request.getParameter("type3") != null) types.add("雑誌");
		if(request.getParameter("type4") != null) types.add("コミックス");

		return types;
	}

	public List<String> getPageCount(int booksCount){

		List<String> pageCountList = new ArrayList<>();
		int pageCount = booksCount / 3 + 1;
		if(booksCount % 3 == 0) pageCount--;

		for(int i = 1; i <= pageCount; i++){
			pageCountList.add(String.valueOf(i));
		}

		return pageCountList;
	}
}