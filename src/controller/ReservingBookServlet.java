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

import beans.Book;
import beans.Reservation;
import beans.User;
import service.BookService;
import service.ReservationService;
import service.UserService;


@WebServlet(urlPatterns = { "/reservingBook" })
public class ReservingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("ranking.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		/*ログインしていない時*/
		if(request.getParameter("notLogin") != null){
			request.getSession().setAttribute("loginErrorMessages", "ログインしてください");
			String parameter = getParameter(request);
			response.sendRedirect("./search?" + parameter);
		}else{

			int bookId = Integer.parseInt(request.getParameter("bookId"));
			String userId = (request.getParameter("userId"));
			String toRanking = request.getParameter("fromRanking");
			String toFavorite = request.getParameter("fromFavorite");
			String toSearch = request.getParameter("fromSearch");

			List<Reservation> reservingCheck = new ReservationService().reservingCheck(bookId, userId);

			if(reservingCheck == null){
				int num = Integer.parseInt(request.getParameter("num"));


				if(num ==1){
					Book reservingBook = new BookService().selectBook(bookId);
					User reservingUser = new UserService().selectUser(userId);

					Reservation addReservation = new Reservation();
					addReservation.setUserId(String.valueOf(reservingUser.getId()));
					addReservation.setBookId(String.valueOf(reservingBook.getId()));
					addReservation.setBookName(reservingBook.getName());
					addReservation.setLibraryId(request.getParameter("libraryId"));

					new ReservationService().insert(addReservation);
					new ReservationService().updateStatus(bookId,num);
				}

				if(toRanking != null) response.sendRedirect("./ranking");
				if(toFavorite != null) response.sendRedirect("./favorite");
				if(toSearch != null){
					String parameter = getParameter(request);
					response.sendRedirect("./search?" + parameter);
				}
				return;


			}else if(reservingCheck.size() != 0){

				List<String> messages = new ArrayList<String>();
				HttpSession session = request.getSession();

				messages.add("すでに予約されています");
				session.setAttribute("errorMessages", messages);

				if(toRanking != null) response.sendRedirect("./ranking");
				if(toFavorite != null) response.sendRedirect("./favorite");
				if(toSearch != null){
					String parameter = getParameter(request);
					response.sendRedirect("./search?" + parameter);
				}
				return;
			}

			int num = Integer.parseInt(request.getParameter("num"));

			if(num ==1){

				Book reservingBook = new BookService().selectBook(bookId);
				User reservingUser = new UserService().selectUser(userId);

				Reservation addReservation = new Reservation();
				addReservation.setUserId(String.valueOf(reservingUser.getId()));
				addReservation.setBookId(String.valueOf(reservingBook.getId()));
				addReservation.setBookName(reservingBook.getName());
				addReservation.setLibraryId(request.getParameter("libraryId"));

				new ReservationService().insert(addReservation);
				new ReservationService().updateStatus(bookId,num);
			}

			if(toRanking != null) response.sendRedirect("./ranking");
			if(toFavorite != null) response.sendRedirect("./favorite");
			if(toSearch != null){
				String parameter = getParameter(request);
				response.sendRedirect("./search?" + parameter);
			}
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
