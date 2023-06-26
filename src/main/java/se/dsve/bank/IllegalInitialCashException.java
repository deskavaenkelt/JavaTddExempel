package se.dsve.bank;

public class IllegalInitialCashException extends RuntimeException {
    public IllegalInitialCashException(String message) {
        super(message);
    }
}
