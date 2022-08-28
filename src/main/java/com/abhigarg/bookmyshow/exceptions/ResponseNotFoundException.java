package com.abhigarg.bookmyshow.exceptions;
import com.abhigarg.bookmyshow.pojo.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Data;
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResponseNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private transient ApiResponse apiResponse;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResponseNotFoundException(ApiResponse apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

}
