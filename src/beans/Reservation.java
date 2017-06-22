package beans;

public class Reservation {

	private int id;
	private String reservedDate;
	private String userId;
	private String userName;
	private String bookId;
	private String bookName;
	private String libraryId;
	private String delivering;
	private String canceling;
	private String point;


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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
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
