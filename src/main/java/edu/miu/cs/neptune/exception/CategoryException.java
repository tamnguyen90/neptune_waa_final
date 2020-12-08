package edu.miu.cs.neptune.exception;

public class CategoryException extends RuntimeException {

    private String message;

    public CategoryException(String message) {
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

