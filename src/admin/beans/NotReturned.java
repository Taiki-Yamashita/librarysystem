package admin.beans;

public class NotReturned {
	private int id;
	private String userName;
	private String bookName;
	private String libraryName;
	private String limitedDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLimitedDate() {
		return limitedDate;
	}
	public void setLimitedDate(String limitedDate) {
		this.limitedDate = limitedDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
}
