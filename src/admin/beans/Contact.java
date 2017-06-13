package admin.beans;

public class Contact {

	private int id;
	private String userId;
	private String bookId;
	private String contactDate;
	private String limitedDate;
	private String finishing;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getContactDate() {
		return contactDate;
	}
	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}
	public String getLimitedDate() {
		return limitedDate;
	}
	public void setLimitedDate(String limitedDate) {
		this.limitedDate = limitedDate;
	}
	public String getFinishing() {
		return finishing;
	}
	public void setFinishing(String finishing) {
		this.finishing = finishing;
	}

}
