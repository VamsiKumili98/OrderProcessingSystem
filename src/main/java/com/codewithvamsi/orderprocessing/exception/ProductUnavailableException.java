package com.codewithvamsi.orderprocessing.exception;

import org.springframework.stereotype.Component;

public class ProductUnavailableException extends RuntimeException {
    public ProductUnavailableException(String message){
        super(message);
    }
}
