package com.example.FakeCommerce.Exeptions;

import com.example.FakeCommerce.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<ApiResponse<Void>> handelResourceNotFoundExeption(ResourceNotFoundExeption ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage(),"Resource Not found"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handelMethodArgumentTypeMismatchExeption(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Invalid value for parameter: " + ex.getName(), "Bad Request"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handelAllGeneralExeptions(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Something went worng","Internal Server Error"));
    }
}
