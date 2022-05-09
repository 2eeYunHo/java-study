package prob02;

public class Book {
	
	private int bookNo;
	private String title;
	private String author;
	private int stateCode; // 0이면 대여중 1이면 재고있음
	
	public Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}

	public int getBookNo() {
		return bookNo;
	}


	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStateCode() {
		return stateCode;
	}
	
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public void print() {
		System.out.println("책 넘버 : "+bookNo+
				" 책제목 : "+title+
				" 작가 : "+author+
				" 대여 유무 : "+(stateCode==1 ? "재고있음" : "대여중"));
		return;
	}
	public void rent() {
		if(stateCode==1) {
			setStateCode(0);
			System.out.println(title+"이(가) 대여 됐습니다.");
		}
	}
}
