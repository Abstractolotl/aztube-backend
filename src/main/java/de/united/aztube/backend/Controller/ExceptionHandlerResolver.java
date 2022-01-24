package de.united.aztube.backend.Controller;

import de.united.aztube.backend.Model.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Component
@ControllerAdvice
public class ExceptionHandlerResolver {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody  ErrorResponse handleException(MethodArgumentNotValidException ex){
        ErrorResponse response = new ErrorResponse();
        response.setError("Bad Request: Malformed. " + ex.getMessage());

        return response;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public @ResponseBody  ErrorResponse handleException(HttpRequestMethodNotSupportedException ex){
        ErrorResponse response = new ErrorResponse();
        response.setError("Bad Request: Method not Allowed. Got: " + ex.getMethod() + " Supported: " + Arrays.toString(ex.getSupportedMethods()));
        return response;
    }

}