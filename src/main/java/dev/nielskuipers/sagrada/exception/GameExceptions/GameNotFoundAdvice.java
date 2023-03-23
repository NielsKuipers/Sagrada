package dev.nielskuipers.sagrada.exception.GameExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GameNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameNotFoundHandler(GameNotFoundException e) {
        return e.getMessage();
    }
}
