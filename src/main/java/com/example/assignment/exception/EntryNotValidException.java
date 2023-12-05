package com.example.assignment.exception;

public class EntryNotValidException extends RuntimeException{
   public EntryNotValidException(String message){
        super(message);
    }
}
