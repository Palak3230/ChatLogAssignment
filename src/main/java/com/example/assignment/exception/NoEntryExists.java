package com.example.assignment.exception;

public class NoEntryExists extends RuntimeException{
    public NoEntryExists(String message){
        super(message);
    }
}
