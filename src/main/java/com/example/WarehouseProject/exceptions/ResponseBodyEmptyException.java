package com.example.WarehouseProject.exceptions;

public class ResponseBodyEmptyException extends RuntimeException{
    public ResponseBodyEmptyException(String message) {
        super(message);
    }
}
