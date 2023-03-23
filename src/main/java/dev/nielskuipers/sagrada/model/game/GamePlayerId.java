package dev.nielskuipers.sagrada.model.game;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class GamePlayerId implements Serializable {
    private int gameId;
    private int playerId;

    public GamePlayerId(int gameId, int playerId){
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public GamePlayerId(){};

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlayerId that = (GamePlayerId) o;
        return gameId == that.gameId && playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}
