package com.vortechgroup.queen.skies.config;


import com.vortechgroup.queen.skies.dto.response.ExceptionResponse;
import com.vortechgroup.queen.skies.utils.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.NoSuchElementException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SuppressWarnings("unused")
@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> exception(NotFoundException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> exception(NoSuchElementException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> exception(MethodArgumentTypeMismatchException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

}