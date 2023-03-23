package dev.nielskuipers.sagrada.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PlayerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNotFoundException e) {
        return e.getMessage();
    }
}
