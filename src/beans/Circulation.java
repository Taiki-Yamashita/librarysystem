package beans;

public class Circulation {

	private int id;
	private String lentDate;
	private String limitedDate;
	private String userId;
	private String userName;
	private String bookId;
	private String bookName;
	private String lending;

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	private String libraryId;
	private String libraryName;
	private String returning;


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLentDate() {
		return lentDate;
	}
	public void setLentDate(String lentDate) {
		this.lentDate = lentDate;
	}
	public String getLimitedDate() {
		return limitedDate;
	}
	public void setLimitedDate(String limitedDate) {
		this.limitedDate = limitedDate;
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
	public String getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}
	public String getReturning() {
		return returning;
	}
	public void setReturning(String returning) {
		this.returning = returning;
	}
	public String getLending() {
		return lending;
	}
	public void setLending(String lending) {
		this.lending = lending;
	}
}