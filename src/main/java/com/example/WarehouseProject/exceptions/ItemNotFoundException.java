package com.example.WarehouseProject.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message) {
        super(message);
    }
}
