package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ticketEvent.domain.dto.ErrorDTO;
import com.example.ticketEvent.exceptions.UserNotFoundException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice 
@Slf4j 
public class GlobalHandleException {

    @ExceptionHandler (UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserException(UserNotFoundException ex){

        log.error("Caught exception : " , ex);
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setError("User not found. ");
        return new ResponseEntity<>(errorDto , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){

         log.error("Caught exception : " , ex);
        ErrorDTO errorDto = new ErrorDTO();
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String message = fieldErrors.stream()
        .findFirst()
        .map(fieldErrors1 -> fieldErrors1.getField() + ":" + fieldErrors1.getDefaultMessage())
        .orElse("Validation error occurred. ");
        
        errorDto.setError(message);
        return new ResponseEntity<>(errorDto , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler (ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex){

        log.error("Caught exception : " , ex);
        ErrorDTO errorDto = new ErrorDTO();
       String message = ex.getConstraintViolations()
        .stream()
        .findFirst()
        .map(violation -> violation.getPropertyPath() + " : " + violation.getMessage())
        .orElse("Constraint violation occurred. ");
        errorDto.setError(message);
        return new ResponseEntity<>(errorDto , HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex){

        log.error("Caught exception : " , ex);
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setError("An unknown error occurred. ");
        return new ResponseEntity<>(errorDto , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
