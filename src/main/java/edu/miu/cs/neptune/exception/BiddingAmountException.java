package edu.miu.cs.neptune.exception;

public class BiddingAmountException extends RuntimeException {

    private String message;

    public BiddingAmountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}