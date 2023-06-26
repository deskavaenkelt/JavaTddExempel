package se.dsve.bank;

public class IllegalCashIsMoreThanMaxTransactionException extends RuntimeException {
    public IllegalCashIsMoreThanMaxTransactionException(String message) {
        super(message);
    }
}