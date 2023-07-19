package com.board.userhandler;

import com.board.entity.DTO.ErrorResponse;
import com.board.entity.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotEqualPasswordException.class)
    public ResponseEntity<ErrorResponse> handleNotEqualPasswordException(NotEqualPasswordException exception) {
        return ErrorResponse.toResponseEntity(exception.getErrorCode());
    }
    //Reqeust Body Validation Exception
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE,
                exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    //Reqeust Param or ModelAttribute Validation Exception
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(BindException exception) {
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE,
                exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        return ErrorResponse.toResponseEntity(exception.getErrorCode());
    }*/
}