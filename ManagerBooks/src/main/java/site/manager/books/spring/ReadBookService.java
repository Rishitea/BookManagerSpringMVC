package site.manager.books.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.manager.books.exception.NotExistingBookException;

@Service
public class ReadBookService {
	
	private BookDao bookDao;
	public ReadBookService() {}
	
	@Autowired
	public ReadBookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public Book read(long no) {
		Book bookData = (Book)bookDao.selectByNo(no);
		if (bookData == null) {
			throw new NotExistingBookException("존재하지 않는 도서입니다.");
		}
		return bookData;
		
	}
	
	public Book readByTitle(String title) {
		Book bookData=(Book)bookDao.selectByTitle(title);
		return bookData;
	}

}
