package dev.nielskuipers.sagrada.exception.CardExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PatternCardNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PatternCardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String patternCardNotFoundHandler(PatternCardNotFoundException e) {
        return e.getMessage();
    }
}
