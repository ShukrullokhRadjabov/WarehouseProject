package com.example.WarehouseProject.exceptions;

public class NameIsBlankException extends RuntimeException{
    public NameIsBlankException(String message) {
        super(message);
    }
}
