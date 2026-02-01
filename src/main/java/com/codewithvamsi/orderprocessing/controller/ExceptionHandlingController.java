package com.codewithvamsi.orderprocessing.controller;

import com.codewithvamsi.orderprocessing.exception.NotFoundException;
import com.codewithvamsi.orderprocessing.exception.ProductUnavailableException;
import com.codewithvamsi.orderprocessing.exception.TimeOutException;
import com.codewithvamsi.orderprocessing.model.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(ProductUnavailableException.class)
    public ErrorResponse badRequestError(ProductUnavailableException ex){
        return new ErrorResponse("409", ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse notFoundError(NotFoundException ex){
        return new ErrorResponse("400", ex.getMessage());
    }

    @ExceptionHandler(TimeOutException.class)
    public ErrorResponse TimeoutError(TimeOutException ex){
        return new ErrorResponse("504", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse InternalServerError(Exception e){
        System.out.println(e.getMessage());
        return new ErrorResponse("500", "Internal Server Error");
    }
}
