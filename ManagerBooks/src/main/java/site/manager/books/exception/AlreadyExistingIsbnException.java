package site.manager.books.exception;

public class AlreadyExistingIsbnException extends RuntimeException{
	public AlreadyExistingIsbnException(String message) {
		super(message);
	}

}
