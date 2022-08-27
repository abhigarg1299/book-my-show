package com.abhigarg.bookmyshow.exceptions;

import com.abhigarg.bookmyshow.pojo.ApiResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@Data
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ApiResponse apiResponse;

    private String message;

    public UnauthorizedException(ApiResponse apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public UnauthorizedException(String message) {
        super(message);
        this.message = message;
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}