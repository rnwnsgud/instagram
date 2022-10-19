package snsProject.photogram.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import snsProject.photogram.handler.exception.CustomValidationException;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public String validationException(RuntimeException e) {
//        return e.getMessage();
//    }

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> validationException(CustomValidationException e) {
        return e.getErrorMap();
    }
}
