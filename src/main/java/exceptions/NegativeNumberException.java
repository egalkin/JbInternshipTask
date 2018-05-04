package exceptions;

public class NegativeNumberException extends Exception{

    public NegativeNumberException() {
        super("Given number is negative.");
    }

    public NegativeNumberException(String message) {
        super("Given number is negative. Try positive number less than or equal to: " + message);
    }
}
