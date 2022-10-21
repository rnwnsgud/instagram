package snsProject.photogram.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import snsProject.photogram.dto.CMRespDto;
import snsProject.photogram.handler.exception.CustomValidationException;
import snsProject.photogram.script.Script;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<?> validationException(CustomValidationException e) {
//        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    }
}
