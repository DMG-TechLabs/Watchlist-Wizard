
package Exceptions;

public class DatabaseStringOverflowException extends RuntimeException {
        public DatabaseStringOverflowException(String err) {
                super(err);
        }
}
