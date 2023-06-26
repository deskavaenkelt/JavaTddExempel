package se.dsve.bank;

public class IllegalCashIsLessThanMinTransactionException extends RuntimeException {
    public IllegalCashIsLessThanMinTransactionException(String message) {
        super(message);
    }
}