package com.rajeevcode.account.exception;

import com.rajeevcode.account.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
    {
      HashMap<String, String> validationErrors = new HashMap<>();
      ex.getBindingResult().getFieldErrors().forEach(error->{
          validationErrors.put(error.getField(), error.getDefaultMessage());
      });
      return  new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> customerAlreadyExistsExceptionHandler(CustomerAlreadyExistsException ex, WebRequest request)
    {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .errorMessage(ex.getMessage())
                .errorTimeStamp(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build(), HttpStatus.BAD_REQUEST);
    };


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalExceptionHandler(Exception ex, WebRequest request)
    {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .errorTimeStamp(LocalDateTime.now())
                .build(),HttpStatus.INTERNAL_SERVER_ERROR);
    };


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request)
    {
            return new ResponseEntity<>(ErrorResponseDto.builder()
                    .errorMessage(ex.getMessage())
                    .apiPath(request.getDescription(false))
                    .errorTimeStamp(LocalDateTime.now())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build(), HttpStatus.NOT_FOUND);
    }
}
