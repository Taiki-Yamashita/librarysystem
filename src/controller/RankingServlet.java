package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Favorite;
import beans.Library;
import beans.Ranking;
import beans.Reservation;
import beans.User;
import service.BookService;
import service.FavoriteService;
import service.LibraryService;
import service.RankingService;
import service.ReservationService;

@WebServlet(urlPatterns = { "/ranking" })
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User loginUser = (User) request.getSession().getAttribute("loginUser");

		List<Reservation> reservationList = new ReservationService().selectAll();
		List<Ranking> circulations = new RankingService().circulationAll();
		List<Ranking> reservations = new RankingService().reservationAll();
		List<Book> books = new BookService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();

		/*予約数が20以上*/
		int reservingCount = 0;
		for(Integer reservation : isReserving(reservationList, loginUser, books)){
			if(reservation == -10) reservingCount++;
			if(reservingCount >= 20) request.setAttribute("reservationMax", "1");
		}

		if(reservations != null){
			List<Integer> reservationCounts = getReservationCount(reservations);
			request.setAttribute("reservationCounts", reservationCounts);
		}
		if(circulations != null){
			List<Integer> circulationCounts = getCirculationCount(circulations);
			request.setAttribute("circulationCounts", circulationCounts);
		}

		List<Reservation> isReservations = new ReservationService().selectAll();
		List<Favorite> favorites = new FavoriteService().selectAll();

		request.setAttribute("circulations", circulations);
		request.setAttribute("reservations", reservations);
		request.setAttribute("books", books);
		request.setAttribute("libraries", libraries);
		request.setAttribute("loginUser", loginUser);

		request.setAttribute("isReservations", isReservations);
		request.setAttribute("isFavorites", favorites);

		request.getRequestDispatcher("/ranking.jsp").forward(request, response);
	}

	public List<Integer> getReservationCount(List<Ranking> reservations){

		List<Integer> reservationCounts = new ArrayList<>();
		for(Ranking reservation : reservations){
			reservationCounts.add(Integer.parseInt(reservation.getCount()));
		}return reservationCounts;
	}

	public List<Integer> getCirculationCount(List<Ranking> circulations){

		List<Integer> circulationCounts = new ArrayList<>();
		for(Ranking circulation : circulations){
			circulationCounts.add(Integer.parseInt(circulation.getCount()));
		}return circulationCounts;
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
}
