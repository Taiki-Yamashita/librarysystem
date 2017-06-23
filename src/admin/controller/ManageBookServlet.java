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
import beans.Library;
import beans.Reservation;
import service.BookService;
import service.LibraryService;
import service.ReservationService;

@WebServlet(urlPatterns = {"/admin/manageBook"})
public class ManageBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Book> books = new BookService().selectAll();
		List<Library> libraryList = new LibraryService().selectAll();
		List<Book> shelfIdList = new BookService().selectShelfId();
		List<Integer> reservationCounts = getReservationCount(books);
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

			books = new BookService().selectRefinedBook(selectBox, freeWord, condition,
					selectedLibrary, selectedShelfId, isReserving, delay, bookStatus);
			request.setAttribute("books", books);

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

	public List<Integer> getReservationCount(List<Book> books){

		List<Integer> reservationCounts = new ArrayList<>();
		for(Book book : books){
			List<Reservation> reservationList = new ReservationService().select(book.getId());
			if(reservationList == null){
				reservationCounts.add(-1);
			}else{
				reservationCounts.add(reservationList.size());
			}
		}

		return reservationCounts;
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