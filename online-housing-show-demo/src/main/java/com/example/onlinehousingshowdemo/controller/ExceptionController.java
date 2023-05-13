package com.example.onlinehousingshowdemo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.onlinehousingshowdemo.ExceptionHandle.DataNotFoundException;
import com.example.onlinehousingshowdemo.ExceptionHandle.Message;
import com.example.onlinehousingshowdemo.ExceptionHandle.ValidationException;

@RestControllerAdvice
public class ExceptionController {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    Message handle(ValidationException e) {
        return new Message(Message.MessageType.Error, e.getMessages());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataNotFoundException.class)
    Message handle(DataNotFoundException e) {
        return new Message(Message.MessageType.Error, List.of(e.getMessage()));
    }


   

}
