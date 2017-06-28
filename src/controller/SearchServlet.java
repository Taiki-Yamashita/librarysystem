package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Circulation;
import beans.Favorite;
import beans.Library;
import beans.Reservation;
import beans.User;
import service.BookService;
import service.CirculationService;
import service.FavoriteService;
import service.LibraryService;
import service.ReservationService;

@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Library> libraryList = new LibraryService().selectAll();
		request.setAttribute("libraryList", libraryList);

		if(request.getParameter("isSearching") != null){

			/*本の状態(貸出可・貸出中)*/
			String bookStatus = request.getParameter("bookStatus");

			/*フリーワード検索*/
			String selectBox = request.getParameter("selectBox");
			String freeWord = request.getParameter("freeWord");
			String condition = request.getParameter("condition");

			/*絞込み検索*/
			List<String> newBooks = getNewBooks(request);
			List<String> libraries = getLibraries(request);
			List<String> categories = getCategories(request);
			List<String> types = getTypes(request);

			/*並び替え*/
			String sort = request.getParameter("sort");

			/*不正なURLパラメーター*/
			boolean flag = isWrongParameter(bookStatus, selectBox, condition, libraries,
					categories, types, sort, request.getParameter("isSearching"), request);

			/*絞込み結果*/
			if(!flag){
				request.getRequestDispatcher("/search.jsp").forward(request, response);
				return;
			}
			List<Book> selectedBooks = new BookService().getSelectedBooks(selectBox, freeWord, condition, sort, bookStatus,
					newBooks, libraries, categories, types);

			/*値の保持*/
			request.setAttribute("bookStatus", bookStatus);
			request.setAttribute("selectBox", new BookService().getMapCategory().get(selectBox));
			request.setAttribute("selectBoxId", selectBox);
			request.setAttribute("freeWord", freeWord);
			request.setAttribute("condition", condition);
			request.setAttribute("newBooks", newBooks);
			request.setAttribute("libraries", libraries);
			request.setAttribute("categories", categories);
			request.setAttribute("types", types);
			request.setAttribute("sort", sort);
			request.setAttribute("isSearching", request.getParameter("isSearching"));

			if(isValid(selectedBooks, request)){

				List<Favorite> favorites = new FavoriteService().selectAll();
				List<Reservation> reservations = new ReservationService().selectAll();
				List<Library> libraryNames = new LibraryService().selectAll();
				List<Circulation> circulationList = new CirculationService().selectMypage();
				User loginUser = (User) request.getSession().getAttribute("loginUser");


				/*お気に入り・予約・図書館情報*/
				request.setAttribute("isFavorites", isFavorite(favorites, loginUser, selectedBooks));
				request.setAttribute("isReserving", isReserving(reservations, loginUser, selectedBooks));
				request.setAttribute("libraryNames", libraryNames);
				request.setAttribute("reservations", reservations);
				request.setAttribute("circulationList", circulationList);

				/*予約数が20以上*/
				int reservingCount = 0;
				for(Integer reservation : isReserving(reservations, loginUser, selectedBooks)){
					if(reservation == -10) reservingCount++;
					if(reservingCount >= 20) request.setAttribute("reservationMax", "1");
				}

				/*ページ遷移管理*/
				request.setAttribute("pageCountList", getPageCount(selectedBooks.size()));
				if(request.getParameter("pageNumber") == null) request.setAttribute("pageNumber", "1");
				else request.setAttribute("pageNumber", request.getParameter("pageNumber"));

				/*本の情報*/
				request.setAttribute("books", selectedBooks);
				request.setAttribute("booksCount", selectedBooks.size());
			}
			else request.setAttribute("books", null);

		}
		request.getRequestDispatcher("/search.jsp").forward(request, response);
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
		int pageCount = booksCount / 10 + 1;
		if(booksCount % 10 == 0) pageCount--;

		for(int i = 1; i <= pageCount; i++){
			pageCountList.add(String.valueOf(i));
		}

		return pageCountList;
	}

	public List<Integer> isFavorite(List<Favorite> favorites, User loginUser, List<Book> books){

		List<Integer> isFavorite = new ArrayList<>();
		if(favorites != null && loginUser != null){
			int cnt = 0;
			for(Book book : books){
				boolean favoriteFlag = false;
				for(Favorite favorite : favorites){
					if(favorite.getBookId().equals(String.valueOf(book.getId())) && favorite.getUserId().equals(String.valueOf(loginUser.getId()))){
						favoriteFlag = true;
					}
				}
				if(favoriteFlag == true) isFavorite.add(-10);
				else isFavorite.add(cnt);
				cnt++;
			}
			return isFavorite;
		}else{
			int cnt = 0;
			for(Book book : books){
				isFavorite.add(cnt);
				cnt++;
			}
		}
		return isFavorite;
	}

	public List<Integer> isReserving(List<Reservation> reservations, User loginUser, List<Book> books){

		List<Integer> isReserving = new ArrayList<>();
		if(reservations != null && loginUser != null){
			int cnt = 0;
			for(Book book : books){
				boolean reservationFlag = false;
				for(Reservation reservation : reservations){
					if(reservation.getBookId().equals(String.valueOf(book.getId())) && reservation.getUserId().equals(String.valueOf(loginUser.getId()))){
						if(reservation.getCanceling().equals("0") && reservation.getDelivering().equals("0")) reservationFlag = true;
					}
				}
				if(reservationFlag == true){
					isReserving.add(-10);
				}
				else isReserving.add(cnt);
				cnt++;
			}
			return isReserving;
		}else{
			int cnt = 0;
			for(Book book : books){
				isReserving.add(cnt);
				cnt++;
			}
		}

		return isReserving;
	}

	public boolean isWrongParameter(String bookStatus, String selectBox, String condition, List<String> libraries,
			List<String> categories, List<String> types, String sort, String isSearching, HttpServletRequest request){

		boolean flag = true;
		Pattern p = Pattern.compile("^\\d{1}$");

		/*bookStatus*/
		boolean bookStatusFlag = false;
		if(p.matcher(bookStatus).find()){
			for(int i = 1; i <= 3; i++){
				if(String.valueOf(i).equals(bookStatus)){
					bookStatusFlag = true;
					break;
				}
			}
		}
		if(!bookStatusFlag) flag = false;

		/*selectBox*/
		boolean selectBoxFlag = false;
		if(p.matcher(selectBox).find()){
			for(int i = 1; i <= 5; i++){
				if(String.valueOf(i).equals(selectBox)){
					selectBoxFlag = true;
					break;
				}
			}
		}
		if(!selectBoxFlag) flag = false;

		/*condition*/
		boolean conditionFlag = false;
		if(p.matcher(condition).find()){
			for(int i = 1; i <= 4; i++){
				if(String.valueOf(i).equals(condition)){
					conditionFlag = true;
					break;
				}
			}
		}
		if(!conditionFlag) flag = false;

		/*sort*/
		boolean sortFlag = false;
		if(p.matcher(sort).find()){
			for(int i = 0; i <= 6; i++){
				if(String.valueOf(i).equals(sort)){
					sortFlag = true;
					break;
				}
			}
		}
		if(!sortFlag) flag = false;

		/*isSearching*/
		if(isSearching.equals("1") || isSearching == null);
		else flag = false;

		if(!flag){
			request.getSession().setAttribute("errorMessages", "不正なURLです");
			return false;
		}
		return true;
	}
}