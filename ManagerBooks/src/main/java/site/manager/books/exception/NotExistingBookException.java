package site.manager.books.exception;

public class NotExistingBookException extends RuntimeException{
	public NotExistingBookException(String message) {
		super(message);
	}

}
