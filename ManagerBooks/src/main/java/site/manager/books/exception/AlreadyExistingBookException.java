package site.manager.books.exception;

public class AlreadyExistingBookException extends RuntimeException{
	public AlreadyExistingBookException(String message) {
		super(message);
	}

}
