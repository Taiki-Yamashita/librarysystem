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

		List<Ranking> circulations = new RankingService().circulationAll();
		List<Ranking> reservations = new RankingService().reservationAll();
		List<Book> books = new BookService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();

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
}
