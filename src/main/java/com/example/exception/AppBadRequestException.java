package com.example.exception;public class AppBadRequestException extends RuntimeException {    public AppBadRequestException(String e) {        super(e);    }}