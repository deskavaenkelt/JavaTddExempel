package se.dsve.bank;

public class IllegalCashIsMoreThanBalanceException extends RuntimeException {
    public IllegalCashIsMoreThanBalanceException(String message) {
        super(message);
    }
}