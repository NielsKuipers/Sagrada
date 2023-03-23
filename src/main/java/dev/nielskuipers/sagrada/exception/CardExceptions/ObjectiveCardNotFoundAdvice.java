package dev.nielskuipers.sagrada.exception.CardExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ObjectiveCardNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ObjectiveCardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String objectiveCardNotFoundHandler(ObjectiveCardNotFoundException e) {
        return e.getMessage();
    }
}
