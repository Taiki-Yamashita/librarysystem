package beans;

public class Ranking {

	private String bookId;
	private String bookName;
	private String circulationBook;
	private String reservationBook;
	private String favoriteBook;
	private String count;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getCirculationBook() {
		return circulationBook;
	}
	public void setCirculationBook(String circulationBook) {
		this.circulationBook = circulationBook;
	}
	public String getReservationBook() {
		return reservationBook;
	}
	public void setReservationBook(String reservationBook) {
		this.reservationBook = reservationBook;
	}
	public String getFavoriteBook() {
		return favoriteBook;
	}
	public void setFavoriteBook(String favoriteBook) {
		this.favoriteBook = favoriteBook;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
