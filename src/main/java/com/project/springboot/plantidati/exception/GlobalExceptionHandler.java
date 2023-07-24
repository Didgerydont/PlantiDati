package com.project.springboot.plantidati.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// The @ControllerAdvice annotation allows for global exception handling in your Spring application.
// This class will be applied to all controllers that are annotated with @RestController.
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {


    // This method will be invoked anytime a MethodArgumentNotValidException is thrown in the application.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Creating a map to hold the error details
        Map<String, String> errors = new HashMap<>();

        // The getBindingResult method returns an object that holds the result of the validation.
        // The getAllErrors method returns a list of all validation errors.
        // Loop through all of the errors using the forEach method.
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            // Get the name of the field that caused the error using getField
            // and the error message provided in the validation constraints annotation using getDefaultMessage.
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            // Adding the field name and the error message to our errors map
            errors.put(fieldName, errorMessage);
        });

        // Returning a ResponseEntity containing the errors map and a BAD_REQUEST status.
        // ResponseEntity represents the whole HTTP response, including body, headers, and status.
        // Send a JSON Response so that we can display the error on the client side.
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}