package com.project.springboot.plantidati.exception;

public class CalendarNotFoundException extends RuntimeException {
    public CalendarNotFoundException(String message) {
        super(message);
    }
}