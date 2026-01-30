package com.codewithvamsi.orderprocessing.controller;

import com.codewithvamsi.orderprocessing.exception.NotFoundException;
import com.codewithvamsi.orderprocessing.exception.ProductUnavailableException;
import com.codewithvamsi.orderprocessing.model.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(ProductUnavailableException.class)
    public ErrorResponse badRequestError(String message){
        return new ErrorResponse("409", message);
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse notFoundError(String message){
        return new ErrorResponse("400", message);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse InternalServerError(){
        return new ErrorResponse("500", "Internal Server Error");
    }
}
