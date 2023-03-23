package dev.nielskuipers.sagrada.exception.PlayerExceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(int playerId) {
        super("Could not find player with id: " + playerId);
    }
}
