package com.codewithvamsi.orderprocessing.exception;

public class TimeOutException extends RuntimeException{
    public TimeOutException(String message){
        super(message);
    }
}
