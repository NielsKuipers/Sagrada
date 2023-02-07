package dev.nielskuipers.sagrada.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(int gameId, int playerId) {
        super("Could not find player with id: " + playerId + " in game with id: " + gameId);
    }
}
