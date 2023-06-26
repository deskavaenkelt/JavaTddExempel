package se.dsve.bank;

public class IllegalAccountNrException extends RuntimeException {
    public IllegalAccountNrException(String message) {
        super(message);
    }
}