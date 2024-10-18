package com.trimble.stripewrapper.exceptions;

import com.stripe.exception.StripeException;
import com.trimble.stripewrapper.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<ErrorResponseDTO> handleStripeException(StripeException e){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getStatusCode().toString(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
