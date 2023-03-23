package dev.nielskuipers.sagrada.exception.GameExceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(int id) {
        super("Could not find game with id: " + id);
    }
}
