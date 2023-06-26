package se.dsve.bank;

public class IllegalashIsMoreThanMaxTransactionException extends RuntimeException {
    public IllegalashIsMoreThanMaxTransactionException(String message) {
        super(message);
    }
}