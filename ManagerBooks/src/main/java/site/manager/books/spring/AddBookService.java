package site.manager.books.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.manager.books.exception.AlreadyExistingBookException;
import site.manager.books.exception.AlreadyExistingIsbnException;

@Service
public class AddBookService {
	private BookDao bookDao;
	
	public AddBookService() {}
	@Autowired
	public AddBookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}	
	
	public void add(AddRequest addReq) {
		Book book = (Book)bookDao.selectByTitle(addReq.getTitle());
		if (book != null) {
			throw new AlreadyExistingBookException(addReq.getTitle()+"는 이미 존재하는 도서입니다.");
		}
		Book book2 = (Book)bookDao.selectByIsbn(addReq.getIsbn());
		if (book2 != null) {
			throw new AlreadyExistingIsbnException(addReq.getIsbn()+"는 이미 존재하는 도서입니다.");
		}
		
		Book newBook = 
				new Book(
						addReq.getNo(),
						addReq.getIsbn(),
						addReq.getTitle(),
						addReq.getWriter(),
						addReq.getPublisher(),
						addReq.getPrice(),
						addReq.getImgoriginal(),
						addReq.getImgsaved(),
						addReq.getIntro()
						);
		bookDao.insert(newBook);

	}

}
