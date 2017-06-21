package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


		request.setAttribute("reservationCounts", reservationCounts);

	//	List<Reservation> reservations = new ReservationService().select(Integer.parseInt(bookId));
	//	System.out.println(reservations.size());

		request.setAttribute("books", books);
		request.setAttribute("libraries", libraries);
		//request.setAttribute("reservations", reservations);



		request.getRequestDispatcher("/admin/manageBook.jsp").forward(request, response);

	}

}