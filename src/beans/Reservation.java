package beans;

public class Reservation {

	private int id;
	private String reservedDate;
	private String userId;
	private String bookId;
	private String bookName;
	private String libraryId;
	private String delivering;
	private String canceling;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReservedDate() {
		return reservedDate;
	}
	public void setReservedDate(String reservedDate) {
		this.reservedDate = reservedDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}
	public String getDelivering() {
		return delivering;
	}
	public void setDelivering(String delivering) {
		this.delivering = delivering;
	}
	public String getCanceling() {
		return canceling;
	}
	public void setCanceling(String canceling) {
		this.canceling = canceling;
	}
}
