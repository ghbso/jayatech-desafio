package com.jaya.demo.exception;

public class InvalidCurrency extends RuntimeException{
    public InvalidCurrency(String message){
        super(message);
    }
}
