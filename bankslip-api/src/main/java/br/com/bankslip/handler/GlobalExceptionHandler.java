package br.com.bankslip.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class GlobalExceptionHandler extends DefaultHandlerExceptionResolver {

    private static final String BANKSLIP_NOT_PROVIDED_IN_THE_REQUEST_BODY = "Bankslip not provided in the request body";
    private static final String INVALID_BANKSLIP_PROVIDED = "Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values";


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleBadRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BANKSLIP_NOT_PROVIDED_IN_THE_REQUEST_BODY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation() {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(INVALID_BANKSLIP_PROVIDED);
    }

}
