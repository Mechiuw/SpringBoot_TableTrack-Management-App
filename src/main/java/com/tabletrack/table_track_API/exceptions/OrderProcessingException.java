package com.tabletrack.table_track_API.exceptions;

public class OrderProcessingException extends RuntimeException{
    public OrderProcessingException(String message){
        super(message);
    }
}
