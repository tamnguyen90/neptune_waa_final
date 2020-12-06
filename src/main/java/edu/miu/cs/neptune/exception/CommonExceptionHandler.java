package edu.miu.cs.neptune.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.annotation.Annotation;

public class CommonExceptionHandler implements ExceptionHandler {
    @Override
    public Class<? extends Throwable>[] value() {
        return new Class[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
