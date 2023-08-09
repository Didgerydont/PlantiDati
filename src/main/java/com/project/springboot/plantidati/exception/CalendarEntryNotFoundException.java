package com.project.springboot.plantidati.exception;

public class CalendarEntryNotFoundException extends RuntimeException {

    public CalendarEntryNotFoundException(String message) {
        super(message);
    }
}