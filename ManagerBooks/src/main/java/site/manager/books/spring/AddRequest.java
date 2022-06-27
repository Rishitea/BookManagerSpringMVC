package site.manager.books.spring;

import org.springframework.stereotype.Component;

//커맨드객체 (사용자 요청 정보를 담는 클래스)
@Component
public class AddRequest {
	private Long no;
	private String isbn;
	private String title;
	private String writer;
	private String publisher;
	private int price;
	private String imgoriginal;
	private String imgsaved;
	private String intro;
	
	public AddRequest() {}
	
	public AddRequest(Long no, String isbn, String title, String writer, String publisher, int price,
			String imgoriginal, String imgsaved, String intro) {
		super();
		this.no = no;
		this.isbn = isbn;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.price = price;
		this.imgoriginal = imgoriginal;
		this.imgsaved = imgsaved;
		this.intro = intro;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgoriginal() {
		return imgoriginal;
	}

	public void setImgoriginal(String imgoriginal) {
		this.imgoriginal = imgoriginal;
	}

	public String getImgsaved() {
		return imgsaved;
	}

	public void setImgsaved(String imgsaved) {
		this.imgsaved = imgsaved;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
	

}
