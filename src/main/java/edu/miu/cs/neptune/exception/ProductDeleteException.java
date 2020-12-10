package edu.miu.cs.neptune.exception;

public class ProductDeleteException extends RuntimeException {

    private String message;

    public ProductDeleteException(String message) {
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
