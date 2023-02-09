package dev.nielskuipers.sagrada.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(int playerId) {
        super("Could not find player with id: " + playerId);
    }
}
