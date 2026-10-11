package com.library.libmanagementsystem.exception;


import com.library.libmanagementsystem.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException  {

    @ExceptionHandler(GenreException.class)
    public ResponseEntity<ApiResponse> handelGenreException(GenreException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getMessage(),false));
    }
}
