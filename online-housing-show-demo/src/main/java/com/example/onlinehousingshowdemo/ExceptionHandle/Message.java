package com.example.onlinehousingshowdemo.ExceptionHandle;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Message {

    private MessageType type;
    private List<String> messages;
    public enum MessageType{
        Information, Alert, Error
    }
}
