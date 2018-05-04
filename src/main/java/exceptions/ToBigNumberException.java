package exceptions;

public class ToBigNumberException extends Exception {

    public ToBigNumberException(String message) {
        super("Given number is greater than number of Stepik courses. Try number less than or equal to  " + message);
    }

}
