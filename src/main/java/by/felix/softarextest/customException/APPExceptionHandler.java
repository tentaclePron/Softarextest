package by.felix.softarextest.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APPExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class, APPException.class})
    protected ResponseEntity<Object> handleAPPException(Exception e){
        return new ResponseEntity<>(e, HttpStatus.I_AM_A_TEAPOT);
    }
}
