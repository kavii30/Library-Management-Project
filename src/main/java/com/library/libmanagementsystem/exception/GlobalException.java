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

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ApiResponse> handleBookException(BookException e) {

        // 1. Get the error message from the exception
        String message = e.getMessage();

        // 2. Build the response body (false = the operation failed)
        ApiResponse response = new ApiResponse(message, false);

        // 3. Send it back with status 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    // easier way to write the exception

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse> handelUserException(UserException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getMessage(),false));
    }
}