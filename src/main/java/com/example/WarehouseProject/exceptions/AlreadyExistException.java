package com.example.WarehouseProject.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
