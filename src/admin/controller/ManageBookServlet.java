package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.beans.NotReturned;
import admin.service.NotReturnedService;
import beans.Book;
import beans.Circulation;
import beans.Library;
import beans.Ranking;
import service.BookService;
import service.CirculationService;
import service.LibraryService;
import service.RankingService;

@WebServlet(urlPatterns = {"/admin/manageBook"})
public class ManageBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Ranking> reservations = new RankingService().reservationAll();

		List<Book> books = new BookService().selectAll();
		List<Library> libraryList = new LibraryService().selectAll();
		List<Book> shelfIdList = new BookService().selectShelfId();
		List<Integer> reservationCounts = getReservationCount(reservations);
		List<Integer> notReturnedCounts = getNotReturnedCount(books);

		request.setAttribute("reservationCounts", reservationCounts);
		request.setAttribute("notReturnedCounts", notReturnedCounts);
		request.setAttribute("books", books);
		request.setAttribute("libraryList", libraryList);
		request.setAttribute("shelfIdList", shelfIdList);

		if(request.getParameter("isSearching") != null){

			/*絞込み*/
			String selectBox = request.getParameter("selectBox");
			String freeWord = request.getParameter("freeWord");
			String condition = request.getParameter("condition");
			String selectedLibrary = request.getParameter("selectedLibrary");
			String selectedShelfId = request.getParameter("selectedShelfId");
			String isReserving = request.getParameter("isReserving");
			String delay = request.getParameter("delay");
			String bookStatus = request.getParameter("bookStatus");

			/*絞りこんだ本*/
			books = new BookService().selectRefinedBook(selectBox, freeWord, condition,
					selectedLibrary, selectedShelfId, isReserving, delay, bookStatus);
			/*遅延している本*/
			List<Book> delayBooks = getDelayBook();

			/*遅延の有無を条件に足す*/
			if(delay.equals("1")){
				isValid(books, request);
				request.setAttribute("books", books);
			}
			if(delay.equals("2")){
				List<Book> refinedBooks = new ArrayList<Book>();
				if(delayBooks != null && books != null){
					for(Book book : books){
						boolean flag = true;
						for(Book delayBook : delayBooks){
							if(delayBook.getId() == book.getId()) flag = false;
						}
						if(flag) refinedBooks.add(book);
					}
				}
				isValid(refinedBooks, request);
				request.setAttribute("books", refinedBooks);
			}
			if(delay.equals("3")){
				List<Book> refinedBooks = new ArrayList<Book>();
				if(delayBooks != null && books != null){
					for(Book book : books){
						for(Book delayBook : delayBooks){
							if(delayBook.getId() == book.getId()) refinedBooks.add(book);
						}
					}
				}
				isValid(refinedBooks, request);
				request.setAttribute("books", refinedBooks);
			}

			/*値の保持*/
			request.setAttribute("selectBox", new BookService().getMapCategory().get(selectBox));
			request.setAttribute("selectBoxId", selectBox);
			request.setAttribute("freeWord", freeWord);
			request.setAttribute("condition", condition);
			request.setAttribute("selectedLibrary", selectedLibrary);
			request.setAttribute("selectedShelfId", selectedShelfId);
			request.setAttribute("isReserving", isReserving);
			request.setAttribute("delay", delay);
			request.setAttribute("bookStatus", bookStatus);
		}

		request.getRequestDispatcher("/admin/manageBook.jsp").forward(request, response);

	}

	public List<Book> getDelayBook(){

		List<Circulation> delayBookIdList = new CirculationService().selectDelayBook();
		List<Book> delayBooks = new ArrayList<Book>();
		if(delayBookIdList != null){
			for(Circulation delayBookId : delayBookIdList){
				Book delayBook = new BookService().selectBook(Integer.parseInt(delayBookId.getBookId()));
				if(delayBook != null) delayBooks.add(delayBook);
			}
		}

		return delayBooks;
	}

	public void isValid(List<Book> books, HttpServletRequest request){

		List<String> errorMessages = new ArrayList<>();
		if(books == null || books.isEmpty()){
			errorMessages.add("検索結果が見つかりませんでした");
			request.getSession().setAttribute("errorMessages", errorMessages);
		}
	}

	public List<Integer> getReservationCount(List<Ranking> reservations){

		List<Integer> reservationCounts = new ArrayList<>();
		for(Ranking reservation : reservations){
			if(reservation==null){
				reservationCounts.add(-1);
			}
			if(reservation!=null){	reservationCounts.add(Integer.parseInt(reservation.getCount()));
			System.out.println(reservation.getCount());
			}
		}return reservationCounts;
	}


	public List<Integer> getNotReturnedCount(List<Book> books){

		List<Integer> notReturnedCounts = new ArrayList<>();
		for(Book book : books){
			List<NotReturned> notReturnedList = new NotReturnedService().select(book.getId());
			if(notReturnedList == null){
			notReturnedCounts.add(-1);
			}else{
				notReturnedCounts.add(notReturnedList.size());
			}
		}

		return notReturnedCounts;
	}

}