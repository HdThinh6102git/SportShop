package com.example.sportshopapp.exceptions;

public class ConstraintViolationException extends RuntimeException{
    public ConstraintViolationException(String message){
        super(message);
    }
}
