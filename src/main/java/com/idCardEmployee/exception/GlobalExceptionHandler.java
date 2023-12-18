package com.idCardEmployee.exception;

import com.idCardEmployee.entity.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request){
        String logMessage = String.format("Exception: %s, message: %s, request: %s",
                ex.getClass().getName(), ex.getMessage(), request.getDescription(false));

        LOGGER.info(logMessage, ex);

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({EmployeeNotFoundException.class, CardAccessNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request){
        LOGGER.error(ex.getMessage());

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND,"Not Found",
                details);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CardAccessException.class)
    public ResponseEntity<Object> handleEntityCardException(RuntimeException ex){
        LOGGER.error(ex.getMessage());

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Bad Request",
                details);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
