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
import javax.servlet.http.HttpSession;

import beans.Favorite;
import beans.User;
import service.FavoriteService;

@WebServlet(urlPatterns = { "/favorite" })
public class FavoriteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		HttpSession session = request.getSession();

		if(loginUser == null) {
			List<String> messages = new ArrayList<String>();
			messages.add("ログインしてください");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}



		List<Favorite> favorites = new FavoriteService().selectAll();
		request.setAttribute("favorites", favorites);

		request.setAttribute("loginUser", loginUser);

		request.getRequestDispatcher("/favorite.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*ログインしていない時*/
		if(request.getParameter("notLogin") != null || request.getParameter("notLoginRanking") !=null){
			request.getSession().setAttribute("loginErrorMessages", "ログインしてください");
		}
		else{
			/*お気に入り登録*/
			Favorite favorite = new Favorite();
			favorite.setUserId(request.getParameter("userId"));
			favorite.setBookId(request.getParameter("bookId"));
			new FavoriteService().insert(favorite);
		}

		String parameter = getParameter(request);

		if(request.getParameter("num") == null) {
			response.sendRedirect("./search?" + parameter);
		}
		else if(request.getParameter("num").matches("1")) {
			response.sendRedirect("./ranking");
		}
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

	public String getParameter(HttpServletRequest request){

		/*本の状態(貸出可・貸出中)*/
		String bookStatus = request.getParameter("bookStatus");

		/*フリーワード検索*/
		String selectBox = request.getParameter("selectBox");
		String freeWord = request.getParameter("freeWord");
		String condition = request.getParameter("condition");

		/*絞込み検索*/
		List<String> libraries = getLibraries(request);
		List<String> categories = getCategories(request);
		List<String> types = getTypes(request);

		/*並び替え*/
		String sort = request.getParameter("sort");

		/*ページ番号*/
		String pageNumber = request.getParameter("pageNumber");

		/*検索情報*/
		String isSearching = request.getParameter("isSearching");

		String libraryParameter = "";
		String categoryParameter = "";
		String typeParameter = "";

		for(String library : libraries){
			libraryParameter += "&library" + library + "=" + library;
		}

		for(String category : categories){
			categoryParameter += "&category" + category + "=" + category;
		}

		for(String type : types){
			typeParameter += "&type" + type + "=" + type;
		}

		String parameter = "bookStatus=" + bookStatus + "&selectBox=" + selectBox + "&freeWord=" + freeWord
				+ "&condition=" + condition + libraryParameter + categoryParameter + typeParameter + "&sort=" + sort
				+ "&pageNumber=" + pageNumber + "&isSearching=" + isSearching;

		return parameter;
	}
}