package com.example.controller;

import com.example.exp.AppBadRequestException;
import com.example.exp.AppMethodNotAllowedExeption;
import com.example.exp.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<String>handler(AppBadRequestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(AppMethodNotAllowedExeption.class)
    ResponseEntity<String> notAllowed(UnAuthorizedException e){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    }
}
