package com.example.controller.handler;import com.example.exception.AppBadRequestException;import com.example.exception.AppMethodNotAllowedException;import com.example.exception.ItemNotFoundException;import com.example.exception.UnAuthorizedException;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.ControllerAdvice;import org.springframework.web.bind.annotation.ExceptionHandler;@ControllerAdvicepublic class ExceptionAdviceController {    @ExceptionHandler(AppBadRequestException.class)    public ResponseEntity<String> handler(AppBadRequestException e) {        return ResponseEntity.badRequest().body(e.getMessage());    }    @ExceptionHandler(ItemNotFoundException.class)    public ResponseEntity<String> handler(ItemNotFoundException e) {        return ResponseEntity.badRequest().body(e.getMessage());    }    @ExceptionHandler(UnAuthorizedException.class)    public ResponseEntity<String> handler(UnAuthorizedException e) {        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());    }    @ExceptionHandler(AppMethodNotAllowedException.class)    public ResponseEntity<String> handler(AppMethodNotAllowedException e) {        return ResponseEntity.badRequest().body(e.getMessage());    }}