package Exceptions;

public class InvalidKeyException  extends RuntimeException {

    public InvalidKeyException(String err) {
        super(err);
    }
}
