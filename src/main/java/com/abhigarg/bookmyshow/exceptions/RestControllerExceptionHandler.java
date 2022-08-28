package com.abhigarg.bookmyshow.exceptions;


import com.abhigarg.bookmyshow.pojo.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponse> resolveException(UnauthorizedException exception) {

        ApiResponse apiResponse = exception.getApiResponse();

        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> resolveException(BadRequestException exception) {
        ApiResponse apiResponse = exception.getApiResponse();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}

