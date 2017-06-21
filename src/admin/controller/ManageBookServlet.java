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

		List<Integer> reservationCounts = new ArrayList<>();
		List<Integer> notReturnedCounts = new ArrayList<>();
		List<String> notReturedUser = new ArrayList<>();

		List<Book> books = new BookService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();

		for(Book book : books){
			List<Reservation> reservationList = new ReservationService().select(book.getId());
			if(reservationList == null){
				reservationCounts.add(-1);
			}else{
				reservationCounts.add(reservationList.size());
			}
		}

		for(Book book : books){
			List<NotReturned> notReturnedList = new NotReturnedService().select(book.getId());
			if(notReturnedList == null){
			notReturnedCounts.add(-1);
			}else{
				notReturnedCounts.add(notReturnedList.size());
				notReturedUser.add(book.getUserName());
			}
		}


		request.setAttribute("reservationCounts", reservationCounts);
		request.setAttribute("notReturnedCounts", notReturnedCounts);
		request.setAttribute("notReturedUser", notReturedUser);
	//	List<Reservation> reservations = new ReservationService().select(Integer.parseInt(bookId));
	//	System.out.println(reservations.size());

		request.setAttribute("books", books);
		request.setAttribute("libraries", libraries);
		//request.setAttribute("reservations", reservations);



		request.getRequestDispatcher("/admin/manageBook.jsp").forward(request, response);

	}

}