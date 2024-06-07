package com.bahari.bahari_resto_API.exceptions;

public class OrderProcessingException extends RuntimeException{
    public OrderProcessingException(String message){
        super(message);
    }
}
