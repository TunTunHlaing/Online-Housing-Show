package com.example.onlinehousingshowdemo.ExceptionHandle;


import org.springframework.validation.FieldError;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return errors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
    }

}
