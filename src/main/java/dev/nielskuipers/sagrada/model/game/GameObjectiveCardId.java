package dev.nielskuipers.sagrada.model.game;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GameObjectiveCardId implements Serializable {
    private int gameId;
    private int objectiveCardId;

    public GameObjectiveCardId(int gameId, int objectiveCardId){
        this.gameId = gameId;
        this.objectiveCardId = objectiveCardId;
    }

    public GameObjectiveCardId() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getObjectiveCardId() {
        return objectiveCardId;
    }

    public void setObjectiveCardId(int objectiveCardId) {
        this.objectiveCardId = objectiveCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObjectiveCardId that = (GameObjectiveCardId) o;
        return gameId == that.gameId && objectiveCardId == that.objectiveCardId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, objectiveCardId);
    }
}
