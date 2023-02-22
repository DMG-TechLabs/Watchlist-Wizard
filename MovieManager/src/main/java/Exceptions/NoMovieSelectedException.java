package Exceptions;

public class NoMovieSelectedException extends RuntimeException {

    public NoMovieSelectedException(String err) {
        super(err);
    }
}
