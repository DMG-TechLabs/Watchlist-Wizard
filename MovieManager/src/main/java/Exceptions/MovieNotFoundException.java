package Exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String err) {
        super(err);
    }
}
