package com.stackroute.controller;

import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionControllerAdvice {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<String> exception3(Exception e) {
//        return new ResponseEntity<>("Internal Server Error", HttpStatus.CONFLICT);
//    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<?> MovieAlreadyExistsException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
